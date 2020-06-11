package com.platdmit.simplecloudmanager.app.vm.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class SingleElementViewModelFactory<Repo> extends ViewModelProvider.NewInstanceFactory {
    private Repo mRepo;
    private Class mRepoInterface;
    private long mElementId;

    public SingleElementViewModelFactory(Repo repo, Class<Repo> repoInterface, long elementId) {
        super();
        mRepo = repo;
        mRepoInterface = repoInterface;
        mElementId = elementId;
    }

    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return (T)modelClass.getConstructor(mRepoInterface, long.class).newInstance(mRepo, mElementId);
        } catch (Exception e){
            try {
                return modelClass.newInstance();
            } catch (InstantiationException | IllegalAccessException g) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, g);
            }
        }
    }
}
