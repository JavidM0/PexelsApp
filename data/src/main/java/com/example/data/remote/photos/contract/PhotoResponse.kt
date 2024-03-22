package com.example.data.remote.photos.contract

import com.example.data.remote.image.contract.ImageResponse
import com.google.gson.annotations.SerializedName

data class PhotoResponse(
    @SerializedName("photos") val photos: List<ImageResponse>
)
