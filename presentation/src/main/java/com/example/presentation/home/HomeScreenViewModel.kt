package com.example.presentation.home

import com.example.domain.photos.model.Photo
import com.example.domain.photos.repository.PhotoRepository
import com.example.presentation.mapping.toImageItem
import com.example.presentation.model.ImageItem
import com.example.presentation.utils.RxViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val photoRepository: PhotoRepository): RxViewModel() {

    val imageModels: BehaviorSubject<List<ImageItem>> = BehaviorSubject.create()

    init {
        getImages()
    }

    private fun getImages() {
        photoRepository.getImages()
            .map(::mapToUiModel)
            .subscribeOn(Schedulers.io())
            .subscribeByViewModel {
                imageModels.onNext(it)
            }
    }

    private fun mapToUiModel(response: Photo): List<ImageItem> {
        return response.photos.map {
            it.toImageItem()
        }
    }
}
