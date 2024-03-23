package com.example.presentation.utils

import androidx.lifecycle.ViewModel
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber

open class RxViewModel : ViewModel() {

    private val lifecycleDisposables = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        lifecycleDisposables.clear()
    }

    private fun Disposable.bindToLifecycle(): Disposable {
        lifecycleDisposables.add(this)
        return this
    }

    fun <T : Any> Single<T>.subscribeByViewModel(
        onError: (Throwable) -> Unit = Timber::e,
        onSuccess: (T) -> Unit = {}
    ): Disposable {
        return this
            .subscribe(
                onSuccess,
                onError,
            )
            .bindToLifecycle()
    }

    fun Completable.subscribeByViewModel(
        onError: (Throwable) -> Unit = Timber::e,
        onComplete: () -> Unit = {},
    ): Disposable {
        return this.subscribe(onComplete, onError)
            .bindToLifecycle()
    }

}
