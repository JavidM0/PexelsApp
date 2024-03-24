package com.example.presentation.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.presentation.R
import com.example.presentation.databinding.FragmentDetailsScreenBinding
import com.example.presentation.model.ImageItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsScreen : Fragment(R.layout.fragment_details_screen) {

    private val binding by viewBinding(FragmentDetailsScreenBinding::bind)
    private val viewModel: DetailsScreenViewModel by viewModels()

    private val args: DetailsScreenArgs by navArgs()
    private var item: ImageItem? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        bindViewModelInputs()
    }

    private fun setUpView() {
        item = args.imageItem
        binding.author.text = item?.author
        Glide.with(this)
            .load(item?.imageUrl)
            .into(binding.image)
    }

    private fun bindViewModelInputs() {
        binding.actionAddFavorites.setOnClickListener {
            viewModel.saveBookMarks(checkNotNull(item))
        }
        binding.actionBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
