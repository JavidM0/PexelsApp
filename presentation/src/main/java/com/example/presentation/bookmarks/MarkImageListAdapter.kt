package com.example.presentation.bookmarks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.presentation.databinding.ItemMarkBinding
import com.example.presentation.model.ImageItem

class MarkImageListAdapter(
    private val clickListener: (item: ImageItem) -> Unit
) : ListAdapter<ImageItem, MarkImageViewHolder>(ImageItem.diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarkImageViewHolder {
        return MarkImageViewHolder(
            ItemMarkBinding.inflate(LayoutInflater.from(parent.context)),
            clickListener
        )
    }

    override fun onBindViewHolder(holder: MarkImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
