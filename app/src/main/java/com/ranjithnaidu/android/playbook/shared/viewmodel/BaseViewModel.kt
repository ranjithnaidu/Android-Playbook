package com.ranjithnaidu.android.playbook.shared.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.koin.standalone.KoinComponent

abstract class BaseViewModel : ViewModel(), KoinComponent {
    /**
     * Rx disposable for tiding up rx observables
     */
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    /**
     * Clean up the Rx disposable
     */
    public override fun onCleared() {
        super.onCleared()

        compositeDisposable.clear()
    }

    protected fun Disposable.autoDispose() {
        compositeDisposable.add(this)
    }
}