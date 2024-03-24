package com.example.presentation.home.images

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.presentation.R
import com.example.presentation.databinding.ItemImageBinding
import com.example.presentation.model.ImageItem

class ImageViewHolder(
    private val binding: ItemImageBinding,
    private val clickListener: (item: ImageItem) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(imageItem: ImageItem) = with(binding) {
        rootView.setOnClickListener { clickListener(imageItem) }

        Glide.with(itemView.context)
            .load(imageItem.imageUrl)
            .apply(RequestOptions().placeholder(R.drawable.place_holder))
            .into(image)
    }
}
