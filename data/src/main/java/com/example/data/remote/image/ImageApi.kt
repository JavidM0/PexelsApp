package com.example.data.remote.image

import com.example.data.remote.image.contracts.ImageResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageApi {

    @GET("photos/")
    fun getImages(@Query("api_key") apiKey: String): Single<List<ImageResponse>>
}
