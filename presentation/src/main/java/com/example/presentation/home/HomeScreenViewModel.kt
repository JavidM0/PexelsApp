package com.example.presentation.home

import com.example.domain.categories.model.Category
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
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val photoRepository: PhotoRepository,
    private val collectionRepository: CollectionRepository
) :
    RxViewModel() {

    val imageModels: BehaviorSubject<List<ImageItem>> = BehaviorSubject.create()
    val collectionModels: BehaviorSubject<List<CollectionItem>> = BehaviorSubject.create()

    init {
        getImages()
        getCollections()
    }

    private fun getImages() {
        photoRepository.getImages()
            .map(::mapImageToUiModel)
            .subscribeOn(Schedulers.io())
            .subscribeByViewModel<List<ImageItem>>(
                onError = {
                    it.printStackTrace()
                }
            ) {
                imageModels.onNext(it)
            }
    }

    private fun mapImageToUiModel(response: Photo): List<ImageItem> {
        return response.photos.map {
            it.toImageItem()
        }
    }

    private fun getCollections() {
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

    private fun mapCollectionToUiModel(response: List<Collection>): List<CollectionItem> {
        return response.map {
            it.toCollectionItem()
        }
    }
}
