package com.example.presentation.bookmarks

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.presentation.databinding.ItemMarkBinding
import com.example.presentation.model.ImageItem

class MarkImageViewHolder(
    private val binding: ItemMarkBinding,
    private val clickListener: (item: ImageItem) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(imageItem: ImageItem) = with(binding) {
        rootView.setOnClickListener { clickListener(imageItem) }

        author.text = imageItem.author
        Glide.with(itemView.context)
            .load(imageItem.imageUrl)
            .into(image)
    }
}
