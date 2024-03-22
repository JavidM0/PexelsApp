package com.example.domain.image.model

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("url") val url: String,
    @SerializedName("photographer") val photographer: String
)