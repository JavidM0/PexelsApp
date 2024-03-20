package com.example.presentation.home.collections

import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.databinding.ItemCollectionBinding

class CollectionViewHolder(
    private val binding: ItemCollectionBinding,
    private val clickListener: (item: CollectionItem) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(collectionItem: CollectionItem) {
        itemView.setOnClickListener { clickListener(collectionItem) }
        binding.collection.text = collectionItem.collection
    }
}
