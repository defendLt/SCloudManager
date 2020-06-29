package com.platdmit.simplecloudmanager.app.vm

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
    protected val mCompositeDisposable = CompositeDisposable()
    override fun onCleared() {
        super.onCleared()
        mCompositeDisposable.clear()
    }
}