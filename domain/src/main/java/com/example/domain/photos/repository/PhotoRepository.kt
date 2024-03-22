package com.example.domain.photos.repository

import com.example.domain.photos.model.Photo
import io.reactivex.rxjava3.core.Single

interface PhotoRepository {

    fun getImages(): Single<Photo>
}
