package com.example.presentation.details

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.presentation.R
import com.example.presentation.databinding.ActivityDetailsScreenBinding
import com.example.presentation.model.ImageItem

class DetailsScreen : AppCompatActivity(R.layout.activity_details_screen) {

    private val binding by viewBinding(ActivityDetailsScreenBinding::bind)
    private val viewModel: DetailsScreenViewModel by viewModels()

    private var item: ImageItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpView()
        bindViewModelInputs()
    }

    private fun setUpView() {
        val intent = Intent()
        val imageItem = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(IMAGE_KEY, ImageItem::class.java)
        } else {
            intent.getParcelableExtra(IMAGE_KEY)
        }

        item = imageItem
        binding.author.text = imageItem?.author
        Glide.with(this)
            .load(imageItem?.imageUrl)
            .into(binding.image)
    }

    private fun bindViewModelInputs() {
        binding.actionAddFavorites.setOnClickListener {
            viewModel.saveBookMarks(checkNotNull(item))
        }
    }

    companion object {
        const val IMAGE_KEY = "imageKey"
    }
}
