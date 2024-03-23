package com.example.data.remote.photos.contract

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    val id: Int,
    val photographer: String,
    @SerializedName("src") val imageSrc: ImageSrc,
)

data class ImageSrc(
    @SerializedName("original") val originalImageUrl: String,
)
