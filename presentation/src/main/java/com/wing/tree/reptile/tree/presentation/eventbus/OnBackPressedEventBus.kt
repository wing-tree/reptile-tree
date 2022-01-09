package com.wing.tree.reptile.tree.eventbus

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

class OnBackPressedEventBus private constructor() {
    private val publishSubject by lazy {
        PublishSubject.create<Unit>()
    }

    private fun callOnBackPressed() {
        publishSubject.onNext(Unit)
    }

    companion object {
        private var INSTANCE: OnBackPressedEventBus? = null

        fun callOnBackPressed() {
            instance().callOnBackPressed()
        }

        fun instance(): OnBackPressedEventBus {
            return INSTANCE ?: OnBackPressedEventBus().also {
                INSTANCE = it
            }
        }

        fun subscribe(): Observable<Unit> = instance().publishSubject
    }
}