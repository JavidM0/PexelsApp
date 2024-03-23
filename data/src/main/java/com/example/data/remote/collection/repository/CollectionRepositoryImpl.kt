package com.example.data.remote.collection.repository

import com.example.data.remote.collection.CollectionApi
import com.example.data.remote.mapper.toCollection
import com.example.domain.categories.repository.CollectionRepository
import com.example.domain.collection.model.Collection
import io.reactivex.Single

class CollectionRepositoryImpl(private val api: CollectionApi) : CollectionRepository {

    override fun getCollections(): Single<List<Collection>> {
        return api.getCollections().map { collectionsResponse ->
            collectionsResponse.collections.map { collectionResponse ->
                collectionResponse.toCollection()
            }
        }
    }
}
