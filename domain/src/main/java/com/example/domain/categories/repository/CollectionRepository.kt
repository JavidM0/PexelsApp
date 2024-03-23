package com.example.domain.categories.repository

import io.reactivex.Single
import com.example.domain.collection.model.Collection

interface CollectionRepository {

    fun getCollections(): Single<List<Collection>>
}
