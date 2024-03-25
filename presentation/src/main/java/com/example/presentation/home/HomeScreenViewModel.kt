package com.example.presentation.home

import com.example.domain.categories.repository.CollectionRepository
import com.example.domain.collection.model.Collection
import com.example.domain.photos.model.Photo
import com.example.domain.photos.repository.PhotoRepository
import com.example.presentation.mapping.toCollectionItem
import com.example.presentation.mapping.toImageItem
import com.example.presentation.model.CollectionItem
import com.example.presentation.model.ImageItem
import com.example.presentation.utils.RxViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val photoRepository: PhotoRepository,
    private val collectionRepository: CollectionRepository
) : RxViewModel() {

    val imageModels: BehaviorSubject<List<ImageItem>> = BehaviorSubject.create()
    val collectionModels: BehaviorSubject<List<CollectionItem>> = BehaviorSubject.create()
    val isError: BehaviorSubject<Boolean> = BehaviorSubject.create()

    init {
        getImages()
        getCollections()
    }

    fun onTextChanged(text: String?) {
        if (text.isNullOrBlank()) {
            getImages()
        } else {
            photoRepository.getImagesByCollection(text).handlePhotoResult()
        }
    }

    fun getImages() {
        photoRepository.getImages().handlePhotoResult()
    }

    private fun mapImageToUiModel(response: Photo): List<ImageItem> {
        return response.photos.map {
            it.toImageItem()
        }
    }

    fun getCollections() {
        collectionRepository.getCollections()
            .map(::mapCollectionToUiModel)
            .subscribeOn(Schedulers.io())
            .subscribeByViewModel<List<CollectionItem>>(
                onError = {
                    it.printStackTrace()
                }
            ) {
                collectionModels.onNext(it)
            }
    }

    private fun Single<Photo>.handlePhotoResult() = map(::mapImageToUiModel)
        .subscribeOn(Schedulers.io())
        .subscribeByViewModel<List<ImageItem>>(
            onError = {
                if (it is UnknownHostException) {
                    isError.onNext(true)
                }
            }
        ) {
            isError.onNext(false)
            imageModels.onNext(it)
        }


    private fun mapCollectionToUiModel(response: List<Collection>): List<CollectionItem> {
        return response.map {
            it.toCollectionItem()
        }
    }
}
