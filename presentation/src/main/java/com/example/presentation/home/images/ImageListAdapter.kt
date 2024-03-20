package com.example.presentation.home.images

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.presentation.databinding.ItemImageBinding
import com.example.presentation.model.ImageItem

class ImageListAdapter(
    private val clickListener: (item: ImageItem) -> Unit
) : ListAdapter<ImageItem, ImageViewHolder>(ImageItem.diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ItemImageBinding.inflate(LayoutInflater.from(parent.context)),
            clickListener
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
