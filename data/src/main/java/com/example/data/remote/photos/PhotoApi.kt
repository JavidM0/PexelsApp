package com.example.data.remote.photos

import com.example.data.remote.photos.contract.PhotoResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

private const val API_KEY = "DwUSU1b7Mfcg0JvQ3XxvyAXMdUjudprxTjrmwPzWj0dRiC6alumNmuDN"

interface PhotoApi {

    @Headers("Authorization: $API_KEY")
    @GET("v1/curated")
    fun getPhotos(): Single<PhotoResponse>
}
