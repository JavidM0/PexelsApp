package com.example.presentation.model

import com.example.presentation.utils.DefaultDiffUtilItemCallback

data class CollectionItem(val title: String) {

    companion object {
        val diffUtil = DefaultDiffUtilItemCallback<CollectionItem> { oldItem, newItem ->
            oldItem.title == newItem.title
        }
    }
}
