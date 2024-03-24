package com.example.presentation.details

import com.example.domain.bookmark.repository.BookMarkRepository
import com.example.domain.image.model.Image
import com.example.presentation.model.ImageItem
import com.example.presentation.utils.RxViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val bookMarkRepository: BookMarkRepository,
) : RxViewModel() {

    val isFavorite: BehaviorSubject<Boolean> = BehaviorSubject.create()

    fun checkFavorite(imageId: Int) {
        Completable.create { emitter ->
            isFavorite.onNext(bookMarkRepository.getBookMarkWithId(imageId) != null)
            emitter.onComplete()
        }.subscribeOn(Schedulers.io()).subscribeByViewModel()
    }

    fun saveBookMarks(imageItem: ImageItem) {
        Completable.create { emitter ->
            isFavorite.onNext(true)
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
