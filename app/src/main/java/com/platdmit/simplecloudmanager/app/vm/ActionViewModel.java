package com.platdmit.simplecloudmanager.app.vm;

import com.platdmit.simplecloudmanager.domain.models.Action;
import com.platdmit.simplecloudmanager.domain.repo.ServerActionsRepo;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.rxjava3.processors.BehaviorProcessor;

public class ActionViewModel extends BaseViewModel {
    private static final String TAG = ActionViewModel.class.getSimpleName();
    private ServerActionsRepo mActionsRepo;

    private LiveData<List<Action>> mActions;
    private MutableLiveData<String> mResultMassage = new MutableLiveData<>();

    private final BehaviorProcessor<List<Action>> mContentProvider = BehaviorProcessor.create();

    public ActionViewModel(ServerActionsRepo serverActionsRepo, long serverId) {
        mActionsRepo = serverActionsRepo;

        //Fast fix for prevent overSubscription after resize
        mCompositeDisposable.add(mActionsRepo.getServerActions(serverId).subscribe(mContentProvider::onNext));

        mActions = LiveDataReactiveStreams.fromPublisher(mContentProvider);
    }

    public LiveData<List<Action>> getActionsLiveData() {
        return mActions;
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
