package com.example.presentation.home.collections

import com.example.presentation.utils.DefaultDiffUtilItemCallback

data class CollectionItem(val collection: String) {

    companion object {
        val diffUtil = DefaultDiffUtilItemCallback<CollectionItem> { oldItem, newItem ->
            oldItem.collection == newItem.collection
        }
    }
}
