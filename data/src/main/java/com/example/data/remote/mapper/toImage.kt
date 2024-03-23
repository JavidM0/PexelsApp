package com.example.data.remote.mapper

import com.example.data.remote.photos.contract.ImageResponse
import com.example.domain.image.model.Image

fun ImageResponse.toImage() = Image(
    id = id,
    url = imageSrc.originalImageUrl,
    photographer = photographer
)
