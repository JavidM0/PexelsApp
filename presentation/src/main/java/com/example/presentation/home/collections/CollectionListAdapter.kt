package com.example.presentation.home.collections

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.presentation.databinding.ItemCollectionBinding
import com.example.presentation.model.CollectionItem

class CollectionListAdapter(
    private val clickListener: (item: CollectionItem) -> Unit
) : ListAdapter<CollectionItem, CollectionViewHolder>(CollectionItem.diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        return CollectionViewHolder(
            ItemCollectionBinding.inflate(LayoutInflater.from(parent.context)),
            clickListener
        )
    }

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
