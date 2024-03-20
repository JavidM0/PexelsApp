package com.example.presentation.utils

import androidx.recyclerview.widget.DiffUtil

class DefaultDiffUtilItemCallback<T : Any>(
    private val areContentsTheSameParam: (oldItem: T, newItem: T) -> Boolean = { oldItem, newItem ->
        oldItem == newItem
    },
    private val getChangePayload: ((oldItem: T, newItem: T) -> Any?)? = null,
    private val areItemsTheSameParam: (oldItem: T, newItem: T) -> Boolean = { oldItem, newItem ->
        oldItem == newItem
    }
) : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) = areItemsTheSameParam(oldItem, newItem)

    override fun areContentsTheSame(oldItem: T, newItem: T) =
        areContentsTheSameParam(oldItem, newItem)

    override fun getChangePayload(oldItem: T, newItem: T): Any? =
        getChangePayload?.invoke(oldItem, newItem)
}
