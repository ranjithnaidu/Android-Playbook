package com.ranjithnaidu.android.playbook.utils

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

fun <T> Observable<T>.subscribeAndObserveOnMainThread(onNext: (t: T) -> Unit, onError: (t: Throwable) -> Unit): Disposable {
    return observeOn(AndroidSchedulers.mainThread())
        .subscribe(onNext, onError)
}