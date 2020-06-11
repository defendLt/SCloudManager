package com.platdmit.simplecloudmanager.app.vm;

import androidx.lifecycle.ViewModel;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public abstract class BaseViewModel extends ViewModel {
    protected final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    protected void onCleared() {
        super.onCleared();
        mCompositeDisposable.clear();
    }
}
