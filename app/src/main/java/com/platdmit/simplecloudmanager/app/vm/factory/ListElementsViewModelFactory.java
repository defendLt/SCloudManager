package com.platdmit.simplecloudmanager.app.vm.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ListElementsViewModelFactory<Repo> extends ViewModelProvider.NewInstanceFactory {
    private Repo mRepo;
    private Class mRepoInterface;

    public ListElementsViewModelFactory(Repo repo, Class<Repo> repoInterface) {
        super();
        mRepo = repo;
        mRepoInterface = repoInterface;
    }

    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return (T)modelClass.getConstructor(mRepoInterface).newInstance(mRepo);
        } catch (Exception e){
            try {
                return modelClass.newInstance();
            } catch (InstantiationException | IllegalAccessException g) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, g);
            }
        }
    }
}
