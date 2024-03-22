package com.example.presentation.utils

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
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
        onNext: (T) -> Unit = {}
    ): Disposable {
        return this
            .subscribe(onNext)
            .bindToLifecycle()
    }
}
