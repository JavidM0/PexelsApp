package com.example.data.remote.photos.contract

import com.google.gson.annotations.SerializedName

data class PhotoResponse(
    @SerializedName("photos") val photos: List<ImageResponse>
)
