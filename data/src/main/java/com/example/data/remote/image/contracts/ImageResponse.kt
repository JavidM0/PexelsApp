package com.example.data.remote.image.contracts

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("url") val url: String,
    @SerializedName("photographer") val photographer: String
)