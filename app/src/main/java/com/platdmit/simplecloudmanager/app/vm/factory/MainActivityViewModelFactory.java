package com.platdmit.simplecloudmanager.app.vm.factory;

import com.platdmit.simplecloudmanager.domain.helpers.ActualApiKeyService;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MainActivityViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private ActualApiKeyService mActualApiKeyService;

    public MainActivityViewModelFactory(ActualApiKeyService actualApiKeyService) {
        super();
        mActualApiKeyService = actualApiKeyService;
    }

    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return (T)modelClass.getConstructor(ActualApiKeyService.class).newInstance(mActualApiKeyService);
        } catch (Exception e){
            try {
                return modelClass.newInstance();
            } catch (InstantiationException | IllegalAccessException g) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, g);
            }
        }
    }
}
