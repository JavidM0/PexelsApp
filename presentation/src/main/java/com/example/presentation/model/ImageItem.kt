package com.example.presentation.model

import android.os.Parcelable
import com.example.presentation.utils.DefaultDiffUtilItemCallback
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageItem(val id: Int, val imageUrl: String, val author: String) : Parcelable {

    companion object {
        val diffUtil = DefaultDiffUtilItemCallback<ImageItem> { oldItem, newItem ->
            oldItem.id == newItem.id
        }
    }
}
