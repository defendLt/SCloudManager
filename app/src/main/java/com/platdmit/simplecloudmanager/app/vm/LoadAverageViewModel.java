package com.platdmit.simplecloudmanager.app.vm;

import com.platdmit.simplecloudmanager.domain.models.LoadAverage;
import com.platdmit.simplecloudmanager.domain.repo.ServerLoadAveragesRepo;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.rxjava3.processors.BehaviorProcessor;

public class LoadAverageViewModel extends BaseViewModel{
    private static final String TAG = LoadAverageViewModel.class.getSimpleName();
    private ServerLoadAveragesRepo mLoadAveragesRepo;

    private LiveData<List<LoadAverage>> mLoadAverages;
    private MutableLiveData<String> mResultMassage = new MutableLiveData<>();

    private final BehaviorProcessor<List<LoadAverage>> mContentProvider = BehaviorProcessor.create();

    public LoadAverageViewModel(ServerLoadAveragesRepo serverLoadAveragesRepo, long serverId) {
        mLoadAveragesRepo = serverLoadAveragesRepo;

        //Fast fix for prevent overSubscription after resize
        mCompositeDisposable.add(mLoadAveragesRepo.getServerLoadAverages(serverId).subscribe(mContentProvider::onNext));

        mLoadAverages = LiveDataReactiveStreams.fromPublisher(mContentProvider);
    }
    public LiveData<List<LoadAverage>> getLoadAveragesLiveData() {
        return mLoadAverages;
    }

    public MutableLiveData<String> getResultMassage() {
        return mResultMassage;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mContentProvider.onComplete();
    }
}
