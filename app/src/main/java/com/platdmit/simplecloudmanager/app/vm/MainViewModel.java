package com.platdmit.simplecloudmanager.app.vm;

import com.platdmit.simplecloudmanager.domain.helpers.ActualApiKeyService;

import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private static final String TAG = LoginViewModel.class.getSimpleName();
    private ActualApiKeyService mActualApiKeyService;

    public MainViewModel(ActualApiKeyService actualApiKeyService) {
        mActualApiKeyService = actualApiKeyService;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mActualApiKeyService.stopAutoUpdate();
    }
}
