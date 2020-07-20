package com.platdmit.simplecloudmanager.vm

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.processors.BehaviorProcessor

abstract class BaseViewModel<T> : ViewModel() {
    protected val compositeDisposable = CompositeDisposable()
    protected val stateProvider: BehaviorProcessor<T> = BehaviorProcessor.create<T>()
    protected val messageProvider: BehaviorProcessor<String> = BehaviorProcessor.create<String>()

    override fun onCleared() {
        super.onCleared()
        messageProvider.onComplete()
        compositeDisposable.clear()
    }
}