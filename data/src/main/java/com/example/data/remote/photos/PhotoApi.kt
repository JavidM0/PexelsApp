package com.example.data.remote.photos

import com.example.data.remote.photos.contract.PhotoResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface PhotoApi {

    @Headers("Authorization: DwUSU1b7Mfcg0JvQ3XxvyAXMdUjudprxTjrmwPzWj0dRiC6alumNmuDN")
    @GET("photos/curated")
    fun getPhotos(): Single<PhotoResponse>
}
