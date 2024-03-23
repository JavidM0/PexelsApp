package com.example.presentation.mapping

import com.example.domain.collection.model.Collection
import com.example.presentation.model.CollectionItem

fun Collection.toCollectionItem() = CollectionItem(
    title = title
)
