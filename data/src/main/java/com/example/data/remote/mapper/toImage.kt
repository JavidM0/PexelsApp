package com.example.data.remote.mapper

import com.example.data.remote.image.contract.ImageResponse
import com.example.domain.image.model.Image

fun ImageResponse.toImage() = Image(
    url = url,
    photographer = photographer
)