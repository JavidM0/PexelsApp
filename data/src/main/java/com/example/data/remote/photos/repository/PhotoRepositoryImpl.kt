package com.example.data.remote.photos.repository

import com.example.data.remote.mapper.toPhoto
import com.example.data.remote.photos.PhotoApi
import com.example.domain.photos.model.Photo
import com.example.domain.photos.repository.PhotoRepository
import io.reactivex.Single

class PhotoRepositoryImpl(private val api: PhotoApi) : PhotoRepository {

    override fun getImages(): Single<Photo> = api.getPhotos().map { it.toPhoto() }
    override fun getImagesByCollection(collection: String): Single<Photo> =
        api.getPhotosByCollection(collection).map { it.toPhoto() }
}
