package com.platdmit.simplecloudmanager.app.vm;

import com.platdmit.simplecloudmanager.domain.models.Server;
import com.platdmit.simplecloudmanager.domain.repo.ServerBaseRepo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.processors.BehaviorProcessor;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ServerViewModel extends BaseViewModel {
    private static final String TAG = ServerViewModel.class.getSimpleName();
    private ServerBaseRepo mServerRepo;

    private LiveData<Server> mServer;
    private MediatorLiveData<String> mResultMassage = new MediatorLiveData<>();

    private final BehaviorProcessor<Server> mContentProvider = BehaviorProcessor.create();

    public ServerViewModel(ServerBaseRepo serverBaseRepo, long id) {
        mServerRepo = serverBaseRepo;

        //Fast fix for prevent overSubscription after resize
        mCompositeDisposable.add(
                mServerRepo.getServer(id)
                        .subscribeOn(Schedulers.newThread())
                        .onErrorComplete(throwable -> {
                            System.out.println(throwable.getMessage());
                            return true;
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(mContentProvider::onNext)
        );

        mServer = LiveDataReactiveStreams.fromPublisher(mContentProvider);

    }

    public LiveData<Server> getServerLiveData() {
        return mServer;
    }

    public MediatorLiveData<String> getResultMassage() {
        return mResultMassage;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mContentProvider.onComplete();
    }
}
