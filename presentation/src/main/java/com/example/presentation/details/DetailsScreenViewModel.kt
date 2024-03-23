package com.example.presentation.details

import com.example.domain.bookmark.repository.BookMarkRepository
import com.example.domain.image.model.Image
import com.example.presentation.model.ImageItem
import com.example.presentation.utils.RxViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val bookMarkRepository: BookMarkRepository,
) : RxViewModel() {

    fun saveBookMarks(imageItem: ImageItem) {
        Completable.create { emitter ->
            bookMarkRepository.saveBookMarks(
                Image(
                    id = imageItem.id,
                    url = imageItem.imageUrl,
                    photographer = imageItem.author
                )
            )
            emitter.onComplete()
        }.subscribeOn(Schedulers.io()).subscribeByViewModel()
    }
}
