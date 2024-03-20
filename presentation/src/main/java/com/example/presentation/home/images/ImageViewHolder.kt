package com.example.presentation.home.images

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.presentation.databinding.ItemImageBinding
import com.example.presentation.model.ImageItem

class ImageViewHolder(
    private val binding: ItemImageBinding,
    private val clickListener: (item: ImageItem) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(imageItem: ImageItem) {
        itemView.setOnClickListener { clickListener(imageItem) }

        Glide.with(itemView.context)
            .load(imageItem.imageUrl)
            .into(binding.image)
    }
}
