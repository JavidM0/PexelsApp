package com.example.data.remote.mapper

import com.example.data.remote.photos.contract.PhotoResponse
import com.example.domain.photos.model.Photo

fun PhotoResponse.toPhoto() = Photo(
    photos = photos.map {
        it.toImage()
    }
)
