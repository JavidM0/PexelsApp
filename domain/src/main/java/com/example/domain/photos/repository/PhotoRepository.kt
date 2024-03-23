package com.example.domain.photos.repository

import com.example.domain.photos.model.Photo
import io.reactivex.Single

interface PhotoRepository {

    fun getImages(): Single<Photo>
}
