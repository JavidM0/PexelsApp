package com.example.data.remote.mapper

import com.example.data.remote.collection.contract.CollectionResponse
import com.example.domain.collection.model.Collection

fun CollectionResponse.toCollection() = Collection(
    title = title
)
