package com.platdmit.simplecloudmanager.app.vm;

import com.platdmit.simplecloudmanager.domain.helpers.ActualApiKeyService;
import com.platdmit.simplecloudmanager.domain.models.UserAccount;
import com.platdmit.simplecloudmanager.domain.repo.AccountRepo;

import org.mindrot.BCrypt;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginViewModel extends BaseViewModel {
    private static final String TAG = LoginViewModel.class.getSimpleName();
    private AccountRepo mAccountRep;

    private MutableLiveData<LoginFormStatus> mAuthStatus = new MutableLiveData<>();
    private MutableLiveData<LoginFormStatus> mRegStatus = new MutableLiveData<>();
    private UserAccount mUserAccount;
    private ActualApiKeyService mActualApiKeyService;

    public LoginViewModel(AccountRepo accountRep, ActualApiKeyService actualApiKeyService) {
        mAccountRep = accountRep;

        mActualApiKeyService = actualApiKeyService;

        mCompositeDisposable.add(mAccountRep.getActiveAccount()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userAccount -> {
                    if (!userAccount.getPin().isEmpty()) {
                        mUserAccount = userAccount;
                        mAuthStatus.postValue(LoginFormStatus.YES_ACTIVE_USER);
                    } else {
                        mAuthStatus.postValue(LoginFormStatus.NEED_SET_PIN);
                    }
                }, fallUser -> {
                    mAuthStatus.postValue(LoginFormStatus.NOT_ACTIVE_USER);
                })
        );
    }

    public void addNewAccount(String login, String pass){
        mCompositeDisposable.add(mAccountRep.getPrepareAccountInfo(login, pass)
                .subscribe(userAccount -> {
                    mUserAccount = userAccount;
                    mRegStatus.postValue(LoginFormStatus.NEED_SET_PIN);
                }, fallAccount -> {
                    mRegStatus.postValue(LoginFormStatus.AUTH_INVALID);
                })
        );
    }

    public void addNewAccountPin(String pin){
        mUserAccount.setPin(pin);
        mCompositeDisposable.add(
                mAccountRep.addAccountPin(mUserAccount)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread())
                        .subscribe(() -> {
                            mActualApiKeyService.startAutoUpdate(mUserAccount);
                            mAuthStatus.postValue(LoginFormStatus.SUCCESS);
                        })
        );
    }

    public void checkAccountPin(String pin){
        Completable.fromAction(() -> {
            if (BCrypt.checkpw(pin, mUserAccount.getPin())) {
                mAuthStatus.postValue(LoginFormStatus.LOAD_DATA);
                mActualApiKeyService.startAutoUpdate(mUserAccount);
                successAuth();
            } else {
                mAuthStatus.postValue(LoginFormStatus.PIN_INVALID);
            }
        }).observeOn(Schedulers.newThread()).subscribe();
    }

    private void successAuth() {
        mCompositeDisposable.add(
                mActualApiKeyService.getAccountStatus()
                        .observeOn(Schedulers.newThread())
                        .onErrorComplete()
                        .subscribe(aBoolean -> {
                            if (aBoolean) mAuthStatus.postValue(LoginFormStatus.SUCCESS);
                        })
        );
    }

    public void onDemoAccount(){
        mActualApiKeyService.startDemoMode();
        mAuthStatus.postValue(LoginFormStatus.ON_DEMO);
    }

    public LiveData<LoginFormStatus> getAuthStatus() {
        return mAuthStatus;
    }

    public LiveData<LoginFormStatus> getRegStatus() {
        return mRegStatus;
    }

    public enum LoginFormStatus{
        YES_ACTIVE_USER,
        NOT_ACTIVE_USER,
        PIN_INVALID,
        AUTH_INVALID,
        NEED_SET_PIN,
        LOAD_DATA,
        ON_DEMO,
        SUCCESS
    }
}
