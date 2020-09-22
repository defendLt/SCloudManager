package com.platdmit.simplecloudmanager.base.extensions

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

/**
 * Base handler for adding disposable resource to container
 */
fun Disposable.toComposite(compositeDisposable: CompositeDisposable){
    compositeDisposable.add(this)
}