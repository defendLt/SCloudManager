package com.platdmit.simplecloudmanager.app.vm;

import com.platdmit.simplecloudmanager.domain.models.Backup;
import com.platdmit.simplecloudmanager.domain.repo.ServerBackupRepo;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.rxjava3.processors.BehaviorProcessor;

public class BackupsViewModel extends BaseViewModel {
    private static final String TAG = BackupsViewModel.class.getSimpleName();
    private ServerBackupRepo mBackupRepo;

    private LiveData<List<Backup>> mBackups;
    private MutableLiveData<String> mResultMassage = new MutableLiveData<>();

    private final BehaviorProcessor<List<Backup>> mContentProvider = BehaviorProcessor.create();

    public BackupsViewModel(ServerBackupRepo serverBackupRepo, long serverId) {
        mBackupRepo = serverBackupRepo;

        //Fast fix for prevent overSubscription after resize
        mCompositeDisposable.add(mBackupRepo.getServerBackups(serverId).subscribe(mContentProvider::onNext));

        mBackups = LiveDataReactiveStreams.fromPublisher(mContentProvider);
    }

    public LiveData<List<Backup>> getBackupsLiveData() {
        return mBackups;
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
