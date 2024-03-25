package com.example.presentation.bookmarks

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.presentation.R
import com.example.presentation.databinding.ItemMarkBinding
import com.example.presentation.model.ImageItem

class MarkImageViewHolder(
    private val binding: ItemMarkBinding,
    private val clickListener: (item: ImageItem) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var imageModel: ImageItem? = null

    init {
        itemView.setOnClickListener {
            clickListener(imageModel ?: return@setOnClickListener)
        }
    }

    fun bind(imageItem: ImageItem) = with(binding) {
        imageModel = imageItem
        author.text = imageItem.author
        Glide.with(itemView.context)
            .load(imageItem.imageUrl)
            .apply(RequestOptions().placeholder(R.drawable.placeholder))
            .into(image)
    }
}
