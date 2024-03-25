package com.example.presentation.bookmarks

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.presentation.R
import com.example.presentation.databinding.FragmentBookmarksScreenBinding
import com.example.presentation.utils.SpacesItemDecoration
import com.example.presentation.utils.bind
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarksScreen : Fragment(R.layout.fragment_bookmarks_screen) {

    private var markImageListAdapter: MarkImageListAdapter? = null

    private val binding by viewBinding(FragmentBookmarksScreenBinding::bind)
    private val viewModel: BookmarksScreenViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getBookMarks()
        initRecyclerView()
        setupImagesList()
        bindViewModelOutputs()
        bindViewModelInputs()
    }

    private fun initRecyclerView() {
        markImageListAdapter = MarkImageListAdapter { image ->
            findNavController().navigate(BookmarksScreenDirections.actionBookmarksToDetails(image))
        }
    }

    private fun setupImagesList() = with(binding.images) {
        addItemDecoration(
            SpacesItemDecoration(
                ITEM_DECORATOR_SPACE
            )
        )
        layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        adapter = markImageListAdapter
    }

    private fun bindViewModelInputs() = with(binding) {
        actionExplore.setOnClickListener {
            //TODO navigate to Home screen
        }
    }

    private fun bindViewModelOutputs() = with(viewModel) {
        imageModels.bind(viewLifecycleOwner) {
            binding.errorTitle.isVisible = it.isEmpty()
            binding.actionExplore.isVisible = it.isEmpty()
            markImageListAdapter?.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        markImageListAdapter = null
    }

    companion object {
        private const val ITEM_DECORATOR_SPACE = 20
    }
}
