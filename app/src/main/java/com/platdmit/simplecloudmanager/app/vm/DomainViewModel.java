package com.platdmit.simplecloudmanager.app.vm;

import com.platdmit.simplecloudmanager.domain.models.Domain;
import com.platdmit.simplecloudmanager.domain.repo.DomainBaseRepo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import io.reactivex.rxjava3.processors.BehaviorProcessor;

public class DomainViewModel extends BaseViewModel {
    private static final String TAG = DomainViewModel.class.getSimpleName();
    private DomainBaseRepo mDomainRepo;
    private LiveData<Domain> mDomain;

    private final BehaviorProcessor<Domain> mContentProvider = BehaviorProcessor.create();

    public DomainViewModel(DomainBaseRepo domainBaseRepo, long id) {
        mDomainRepo = domainBaseRepo;

        //Fast fix for prevent overSubscription after resize
        mCompositeDisposable.add(mDomainRepo.getDomain(id).subscribe(mContentProvider::onNext));

        mDomain = LiveDataReactiveStreams.fromPublisher(mContentProvider);
    }

    public LiveData<Domain> getDomainLiveData() {
        return mDomain;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mContentProvider.onComplete();
    }
}
