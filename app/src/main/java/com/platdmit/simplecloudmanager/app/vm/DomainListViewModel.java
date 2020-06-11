package com.platdmit.simplecloudmanager.app.vm;

import com.platdmit.simplecloudmanager.domain.models.Domain;
import com.platdmit.simplecloudmanager.domain.repo.DomainBaseRepo;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.rxjava3.processors.BehaviorProcessor;

public class DomainListViewModel extends BaseViewModel {
    private static final String TAG = DomainListViewModel.class.getSimpleName();
    private DomainBaseRepo mDomainRepo;

    private LiveData<List<Domain>> mDomains;
    private MutableLiveData<String> mResultMassage = new MutableLiveData<>();

    private final BehaviorProcessor<List<Domain>> mContentProvider = BehaviorProcessor.create();

    public DomainListViewModel(DomainBaseRepo domainBaseRepo) {
        mDomainRepo = domainBaseRepo;

        //Fast fix for prevent overSubscription after resize
        mCompositeDisposable.add( mDomainRepo.getDomains().subscribe(mContentProvider::onNext));

        mDomains = LiveDataReactiveStreams.fromPublisher(mContentProvider);
    }

    public void reloadDomainList() {

    }

    public LiveData<List<Domain>> getDomainsLiveData() {
        return mDomains;
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
