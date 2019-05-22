package com.ranjithnaidu.android.playbook.utils

import android.util.Log
import android.view.View
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

private const val CLICK_THROTTLE_WINDOW = 500L

fun View.throttleClicks(): Observable<Unit> {
    return this.clicks().throttleFirst(CLICK_THROTTLE_WINDOW, TimeUnit.MILLISECONDS)
}

fun <T> Observable<T>.subscribeAndObserveOnMainThread(onNext: (t: T) -> Unit, onError: (t: Throwable) -> Unit): Disposable {
    return observeOn(AndroidSchedulers.mainThread())
        .subscribe(onNext, onError)
}

fun <T> Observable<T>.subscribeToViewModelObservable(onNext: (t: T) -> Unit): Disposable {
    return observeOn(AndroidSchedulers.mainThread())
        .subscribe(onNext, {
            Log.e("ViewModelObservable", "View model observable threw error", it)
        })
}