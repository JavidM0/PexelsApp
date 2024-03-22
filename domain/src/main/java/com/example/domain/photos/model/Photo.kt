package com.example.domain.photos.model

import com.example.domain.image.model.Image
import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("photos") val photos: List<Image>
)
