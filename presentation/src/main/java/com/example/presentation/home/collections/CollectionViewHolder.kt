package com.example.presentation.home.collections

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.R
import com.example.presentation.databinding.ItemCollectionBinding
import com.example.presentation.model.CollectionItem

class CollectionViewHolder(
    private val binding: ItemCollectionBinding,
    private val clickListener: (item: CollectionItem) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var collectionModel: CollectionItem? = null

    init {
        itemView.setOnClickListener {
            clickListener(collectionModel ?: return@setOnClickListener)
        }
    }

    fun bind(collectionItem: CollectionItem) = with(binding) {
        collectionModel = collectionItem
        collection.text = collectionItem.title
        collection.background =
            getBackgroundDrawable(
                itemView.context,
                collectionItem.isSelected
            )
        collection.setTextColor(getTextColor(itemView.context, collectionItem.isSelected))
    }

    private fun getBackgroundDrawable(context: Context, isItemSelected: Boolean): Drawable {
        val background = if (isItemSelected) {
            R.drawable.bg_collection_selected
        } else {
            R.drawable.bg_collection
        }
        return checkNotNull(AppCompatResources.getDrawable(context, background))
    }

    private fun getTextColor(context: Context, isItemSelected: Boolean): Int {
        val color = if (isItemSelected) {
            R.color.white
        } else {
            R.color.text
        }
        return context.getColor(color)
    }
}
