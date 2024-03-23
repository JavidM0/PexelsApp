package com.example.data.local.mapper

import com.example.data.local.BookMarkEntity
import com.example.domain.image.model.Image

fun Image.toEntity(): BookMarkEntity = BookMarkEntity(
    uid =  id,
    url = url,
    photographer = photographer
)
