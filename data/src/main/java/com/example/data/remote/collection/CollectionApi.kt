package com.example.data.remote.collection

import com.example.data.remote.collection.contract.CollectionsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

private const val API_KEY = "DwUSU1b7Mfcg0JvQ3XxvyAXMdUjudprxTjrmwPzWj0dRiC6alumNmuDN"

interface CollectionApi {

    @Headers("Authorization: $API_KEY")
    @GET("v1/collections/featured")
    fun getCollections(): Single<CollectionsResponse>
}
