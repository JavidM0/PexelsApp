package com.example.presentation.utils

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import timber.log.Timber

fun <T : Any> Observable<T>.bind(
    lifecycleOwner: LifecycleOwner,
    onError: (Throwable) -> Unit = { Timber.tag(javaClass.name).e(it.stackTraceToString()) },
    onNext: (T) -> Unit = {},
) {
    observeOn(AndroidSchedulers.mainThread()).subscribe(onNext, onError)
        .disposeOnDestroy(lifecycleOwner)
}

private fun Disposable.disposeOnDestroy(lifecycleOwner: LifecycleOwner) {
    lifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {

        override fun onDestroy(owner: LifecycleOwner) {
            super.onDestroy(owner)
            this@disposeOnDestroy.dispose()
        }
    })
}
