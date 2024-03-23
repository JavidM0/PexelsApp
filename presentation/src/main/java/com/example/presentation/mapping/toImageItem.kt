package com.example.presentation.mapping

import com.example.domain.image.model.Image
import com.example.presentation.model.ImageItem

fun Image.toImageItem() = ImageItem(
    id = id,
    imageUrl = url,
    author = photographer
)
