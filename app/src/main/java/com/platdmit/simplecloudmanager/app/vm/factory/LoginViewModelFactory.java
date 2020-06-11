package com.platdmit.simplecloudmanager.app.vm.factory;

import com.platdmit.simplecloudmanager.domain.helpers.ActualApiKeyService;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class LoginViewModelFactory<Repo> extends ViewModelProvider.NewInstanceFactory {
    private Repo mRepo;
    private Class mRepoInterface;
    private ActualApiKeyService mActualApiKeyService;

    public LoginViewModelFactory(Repo repo, Class<Repo> repoInterface, ActualApiKeyService actualApiKeyService) {
        super();
        mRepo = repo;
        mRepoInterface = repoInterface;
        mActualApiKeyService = actualApiKeyService;
    }

    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return (T)modelClass.getConstructor(mRepoInterface, ActualApiKeyService.class).newInstance(mRepo, mActualApiKeyService);
        } catch (Exception e){
            try {
                return modelClass.newInstance();
            } catch (InstantiationException | IllegalAccessException g) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, g);
            }
        }
    }
}
