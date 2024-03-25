package com.example.presentation.details

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import com.example.domain.bookmark.repository.BookMarkRepository
import com.example.domain.image.model.Image
import com.example.presentation.model.ImageItem
import com.example.presentation.utils.RxViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.net.URL
import javax.inject.Inject


@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val bookMarkRepository: BookMarkRepository,
) : RxViewModel() {

    val isError: BehaviorSubject<Boolean> = BehaviorSubject.create()
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

    fun onDownloadClicked(image: ImageItem) {
        Completable.create {
            val url = URL(image.imageUrl)
            val inputStream: InputStream = url.openStream()
            val bitmap = BitmapFactory.decodeStream(inputStream)
            val filePath = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                FILE_NAME + image.id + FILE_FORMAT
            )
            val outputStream: OutputStream = FileOutputStream(filePath)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
            it.onComplete()
        }.subscribeOn(Schedulers.io()).subscribeByViewModel(
            onError = {
                isError.onNext(true)
            }
        ) {
            isError.onNext(false)
        }
    }

    companion object {
        private const val FILE_NAME = "image"
        private const val FILE_FORMAT = ".png"
    }
}
