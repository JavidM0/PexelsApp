package com.example.presentation.home

import android.os.Bundle
import android.view.View
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
import com.example.presentation.utils.SpacesItemDecoration
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
    }

    private fun initRecyclerView() {
        collectionListAdapter = CollectionListAdapter { collection ->

        }

        imageListAdapter = ImageListAdapter { image ->
            findNavController().navigate(HomeScreenDirections.actionHomeToDetails(image))
        }
    }

    private fun setupCollectionsList() = with(binding.collections) {
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        adapter = collectionListAdapter
    }

    private fun setupImagesList() = with(binding.images) {
        addItemDecoration(
            SpacesItemDecoration(
                ITEM_DECORATOR_SPACE
            )
        )
        layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        adapter = imageListAdapter
    }

    private fun bindViewModelOutputs() = with(viewModel) {
        collectionModels.bind(viewLifecycleOwner) {
            collectionListAdapter?.submitList(it)
        }

        imageModels.bind(viewLifecycleOwner) {
            imageListAdapter?.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        imageListAdapter = null
        collectionListAdapter = null
    }

    companion object {
        private const val ITEM_DECORATOR_SPACE = 20
    }
}
