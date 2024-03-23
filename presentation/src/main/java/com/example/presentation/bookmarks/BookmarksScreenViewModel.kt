package com.example.presentation.bookmarks

import com.example.domain.bookmark.repository.BookMarkRepository
import com.example.presentation.mapping.toImageItem
import com.example.presentation.model.ImageItem
import com.example.presentation.utils.RxViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

@HiltViewModel
class BookmarksScreenViewModel @Inject constructor(private val bookMarkRepository: BookMarkRepository) :
    RxViewModel() {

    val imageModels: BehaviorSubject<List<ImageItem>> = BehaviorSubject.create()

    init {
        getBookMarks()
    }

    private fun getBookMarks() {
        Completable.create { emitter ->
            bookMarkRepository.getBookMarks().map {
                it.toImageItem()
            }.also {
                imageModels.onNext(it)
            }
            emitter.onComplete()
        }.subscribeOn(Schedulers.io()).subscribeByViewModel()
    }
}
