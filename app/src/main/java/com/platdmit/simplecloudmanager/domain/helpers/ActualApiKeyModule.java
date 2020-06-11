package com.platdmit.simplecloudmanager.domain.helpers;

import com.platdmit.simplecloudmanager.data.api.ApiAccountRepo;
import com.platdmit.simplecloudmanager.domain.models.UserAccount;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

public class ActualApiKeyModule implements ActualApiKeyService {
    private final String DEMO_API_KEY = "kdDZDD9pNgv1jiakld784riyjiAtXzQj";
    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private BehaviorSubject<Boolean> mValidAccount;

    private String mApiKey;
    private ApiAccountRepo mApiAccountRepo;
    private UserAccount mUserAccount;

    public ActualApiKeyModule(ApiAccountRepo apiAccountRepo) {
        mApiAccountRepo = apiAccountRepo;
    }

    @Override
    public void setActiveAccount(UserAccount userAccount) {
        stopAutoUpdate();
        startAutoUpdate(userAccount);
    }

    @Override
    public void startAutoUpdate(UserAccount activeAccount) {
        mValidAccount = BehaviorSubject.create();
        mUserAccount = activeAccount;
        startAutoUpdater();
    }

    @Override
    public void stopAutoUpdate() {
        mValidAccount.onComplete();
        mCompositeDisposable.clear();
    }

    @Override
    public BehaviorSubject<Boolean> getAccountStatus() {
        return mValidAccount;
    }

    @Override
    public void startDemoMode() {
        setApiKey(DEMO_API_KEY);
    }

    @Override
    public String getApiKey() {
        return mApiKey;
    }

    private void startAutoUpdater() {
        mCompositeDisposable.add(
                mApiAccountRepo.getApiKey(mUserAccount.getLogin(), mUserAccount.getPass())
                        .subscribeOn(Schedulers.newThread())
                        .onErrorComplete(throwable -> {
                            mValidAccount.onNext(false);
                            return true;
                        })
                        .doOnSuccess(apiAuth -> {
                            mValidAccount.onNext(true);
                        })
                        .repeatWhen(updateController -> updateController.filter(o -> mValidAccount.getValue()).delay(25, TimeUnit.MINUTES).onErrorComplete())
                        .subscribe(apiAuth -> setApiKey(apiAuth.getSessionKey()))
        );
    }

    private void setApiKey(String key){
        mApiKey = key;
    }

}
