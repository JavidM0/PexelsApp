package com.example.data.remote.collection.contract

data class CollectionsResponse(
    val collections: List<CollectionResponse>,
)

data class CollectionResponse(
    val title: String,
)
