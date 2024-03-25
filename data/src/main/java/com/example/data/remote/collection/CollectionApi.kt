package com.example.data.remote.collection

import com.example.data.remote.collection.contract.CollectionsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val API_KEY = "DwUSU1b7Mfcg0JvQ3XxvyAXMdUjudprxTjrmwPzWj0dRiC6alumNmuDN"
private const val MAX_COLLECTION_COUNT = 7

interface CollectionApi {

    @Headers("Authorization: $API_KEY")
    @GET("v1/collections/featured")
    fun getCollections(@Query("per_page") countPhoto: Int = MAX_COLLECTION_COUNT): Single<CollectionsResponse>
}
