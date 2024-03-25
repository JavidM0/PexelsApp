package com.example.data.remote.photos

import com.example.data.remote.photos.contract.PhotoResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val API_KEY = "DwUSU1b7Mfcg0JvQ3XxvyAXMdUjudprxTjrmwPzWj0dRiC6alumNmuDN"
private const val MAX_PHOTO_COUNT = 30

interface PhotoApi {

    @Headers("Authorization: $API_KEY")
    @GET("v1/curated")
    fun getPhotos(@Query("per_page") countPhoto: Int = MAX_PHOTO_COUNT): Single<PhotoResponse>

    @Headers("Authorization: $API_KEY")
    @GET("v1/search")
    fun getPhotosByCollection(@Query("query") collection: String): Single<PhotoResponse>
}
