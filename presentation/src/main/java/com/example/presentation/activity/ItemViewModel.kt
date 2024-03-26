package com.example.presentation.activity

import com.example.presentation.utils.RxViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor() : RxViewModel() {

    val selectedItem: BehaviorSubject<NavigationId> = BehaviorSubject.create()

    fun selectItem(item: NavigationId) {
        selectedItem.onNext(item)
    }
}
