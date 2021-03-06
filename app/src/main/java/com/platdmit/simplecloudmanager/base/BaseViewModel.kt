package com.platdmit.simplecloudmanager.base

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.processors.BehaviorProcessor

abstract class BaseViewModel<T> : ViewModel() {
    protected val compositeDisposable = CompositeDisposable()
    protected val stateProvider: BehaviorProcessor<T> = BehaviorProcessor.create<T>()
    protected val messageProvider: BehaviorProcessor<String> = BehaviorProcessor.create<String>()

    override fun onCleared() {
        super.onCleared()
        stateProvider.onComplete()
        messageProvider.onComplete()
        compositeDisposable.clear()
    }
}