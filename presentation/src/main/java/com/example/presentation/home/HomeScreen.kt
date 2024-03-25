package com.example.presentation.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.presentation.R
import com.example.presentation.databinding.FragmentHomeScreenBinding
import com.example.presentation.home.collections.CollectionListAdapter
import com.example.presentation.home.images.ImageListAdapter
import com.example.presentation.utils.CollectionsSpacesItemDecoration
import com.example.presentation.utils.ImagesSpacesItemDecoration
import com.example.presentation.utils.bind
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreen : Fragment(R.layout.fragment_home_screen) {

    private var imageListAdapter: ImageListAdapter? = null
    private var collectionListAdapter: CollectionListAdapter? = null

    private val binding by viewBinding(FragmentHomeScreenBinding::bind)
    private val viewModel: HomeScreenViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        setupCollectionsList()
        setupImagesList()
        bindViewModelOutputs()
        bindViewModelInputs()
    }

    private fun initRecyclerView() {
        collectionListAdapter = CollectionListAdapter { collection ->
            viewModel.onCollectionSelected(collection)
            binding.searchView.setQuery(collection.title, false)
        }

        imageListAdapter = ImageListAdapter { image ->
            findNavController().navigate(HomeScreenDirections.actionHomeToDetails(image))
        }
    }

    private fun setupCollectionsList() = with(binding.collections) {
        addItemDecoration(
            CollectionsSpacesItemDecoration(
                ITEM_DECORATOR_SPACE,
                ITEM_DECORATOR_SPACE_LEFT
            )
        )
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        adapter = collectionListAdapter
    }

    private fun setupImagesList() = with(binding.images) {
        addItemDecoration(
            ImagesSpacesItemDecoration(
                ITEM_DECORATOR_SPACE
            )
        )
        layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        adapter = imageListAdapter
    }

    private fun bindViewModelInputs() = with(binding) {
        actionTryAgain.setOnClickListener {
            viewModel.getImages()
            viewModel.getCollections()
        }

        actionExplore.setOnClickListener {
            viewModel.getImages()
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(text: String?): Boolean {
                viewModel.onTextChanged(text)
                return false
            }
        })
    }

    private fun bindViewModelOutputs() = with(viewModel) {
        collectionModels.bind(viewLifecycleOwner) {
            collectionListAdapter?.submitList(it)
        }

        imageModels.bind(viewLifecycleOwner) { imageList ->
            binding.notFoundError.isVisible = imageList.isEmpty()
            binding.actionExplore.isVisible = imageList.isEmpty()
            binding.images.isVisible = imageList.isNotEmpty()
            imageListAdapter?.submitList(imageList)
        }

        isError.bind(viewLifecycleOwner) {
            binding.noConnectionError.isVisible = it
            binding.actionTryAgain.isVisible = it
            binding.images.isVisible = !it
        }

        isLoading.bind(viewLifecycleOwner) {
            binding.progressbar.isVisible = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        imageListAdapter = null
        collectionListAdapter = null
    }

    companion object {
        private const val ITEM_DECORATOR_SPACE = 20
        private const val ITEM_DECORATOR_SPACE_LEFT = 70
    }
}
