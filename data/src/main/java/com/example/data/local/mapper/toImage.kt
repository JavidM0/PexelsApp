package com.example.data.local.mapper

import com.example.data.local.BookMarkEntity
import com.example.domain.image.model.Image

fun BookMarkEntity.toImage() = Image(
    id = uid,
    url = url,
    photographer = photographer
)