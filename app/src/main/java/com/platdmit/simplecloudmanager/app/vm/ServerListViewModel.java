package com.platdmit.simplecloudmanager.app.vm;

import com.platdmit.simplecloudmanager.domain.models.Server;
import com.platdmit.simplecloudmanager.domain.repo.ServerBaseRepo;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.rxjava3.processors.BehaviorProcessor;

public class ServerListViewModel extends BaseViewModel {
    private static final String TAG = ServerListViewModel.class.getSimpleName();
    private ServerBaseRepo mServerRepo;

    private LiveData<List<Server>> mServers;
    private MutableLiveData<String> mResultMassage = new MutableLiveData<>();
    private final BehaviorProcessor<List<Server>> mContentProvider = BehaviorProcessor.create();

    public ServerListViewModel(ServerBaseRepo serverBaseRepo){
        mServerRepo = serverBaseRepo;

        //Fast fix for prevent overSubscription after resize
        mCompositeDisposable.add(mServerRepo.getServers().subscribe(mContentProvider::onNext));

        mServers = LiveDataReactiveStreams.fromPublisher(mContentProvider);
    }

    public void reloadServerList() {
        mServerRepo.nextUpdate();
    }

    public LiveData<List<Server>> getServersLiveData() {
        return mServers;
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
