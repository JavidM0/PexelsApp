package com.example.data.remote.photos.repository

import com.example.data.remote.mapper.toPhoto
import com.example.data.remote.photos.PhotoApi
import com.example.data.remote.photos.contract.PhotoResponse
import com.example.domain.photos.model.Photo
import com.example.domain.photos.repository.PhotoRepository
import io.reactivex.rxjava3.core.Single

class PhotoRepositoryImpl(private val api: PhotoApi) : PhotoRepository {

    override fun getImages(): Single<Photo> {
        return api.getPhotos().map {
            it.toPhoto()
        }
    }
}
