package com.platdmit.simplecloudmanager.domain.repo;

import com.platdmit.simplecloudmanager.domain.models.UserAccount;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface AccountRepo {
    Single<UserAccount> getActiveAccount();
    Single<UserAccount> getPrepareAccountInfo(String login, String pass);
    Completable addAccountPin(UserAccount account);
}
