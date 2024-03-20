package com.example.domain.repository

import com.example.data.remote.image.ImageApi
import com.example.data.remote.image.contracts.ImageResponse
import io.reactivex.rxjava3.core.Single

class ImageRepository(private val api: ImageApi) {

    fun getImages(apiKey: String): Single<List<ImageResponse>> {
        return api.getImages(apiKey)
    }
}
