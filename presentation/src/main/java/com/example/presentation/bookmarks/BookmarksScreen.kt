package com.example.presentation.bookmarks

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.presentation.R
import com.example.presentation.databinding.FragmentBookmarksScreenBinding
import com.example.presentation.details.DetailsScreen
import com.example.presentation.utils.bind
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarksScreen : Fragment(R.layout.fragment_bookmarks_screen) {

    private var markImageListAdapter: MarkImageListAdapter? = null

    private val binding by viewBinding(FragmentBookmarksScreenBinding::bind)
    private val viewModel: BookmarksScreenViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        bindViewModelOutputs()
    }

    private fun initRecyclerView() {
        markImageListAdapter = MarkImageListAdapter { image ->
            val intent = Intent(requireContext(), DetailsScreen::class.java).also {
                it.putExtra(MARK_IMAGE_KEY, image)
            }
            startActivity(intent)
        }

        binding.images.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = markImageListAdapter
        }
    }

    private fun bindViewModelOutputs() = with(viewModel) {
        imageModels.bind(viewLifecycleOwner) {
            markImageListAdapter?.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        markImageListAdapter = null
    }

    companion object {
        const val MARK_IMAGE_KEY = "imageKey"
    }
}
