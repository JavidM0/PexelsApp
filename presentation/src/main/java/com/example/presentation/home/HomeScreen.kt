package com.example.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.presentation.R
import com.example.presentation.databinding.FragmentHomeScreenBinding
import com.example.presentation.details.DetailsScreen
import com.example.presentation.home.collections.CollectionListAdapter
import com.example.presentation.home.images.ImageListAdapter
import dagger.hilt.EntryPoint

@EntryPoint
class HomeScreen : Fragment(R.layout.fragment_home_screen) {

    private var imageListAdapter: ImageListAdapter? = null
    private var collectionListAdapter: CollectionListAdapter? = null

    private val binding by viewBinding(FragmentHomeScreenBinding::bind)
    private val viewModel: HomeScreenViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        collectionListAdapter = CollectionListAdapter { collection ->

        }

        imageListAdapter = ImageListAdapter { image ->
            val intent = Intent(requireContext(), DetailsScreen::class.java).also {
                it.putExtra(IMAGE_KEY, image)
            }
            startActivity(intent)
        }

        binding.collections.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = collectionListAdapter
        }

        binding.images.run {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = imageListAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }

    companion object {
        const val IMAGE_KEY = "key"
    }
}
