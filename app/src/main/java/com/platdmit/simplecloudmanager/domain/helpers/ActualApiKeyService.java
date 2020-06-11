package com.platdmit.simplecloudmanager.domain.helpers;

import com.platdmit.simplecloudmanager.domain.models.UserAccount;

import io.reactivex.rxjava3.subjects.BehaviorSubject;

public interface ActualApiKeyService {
    void setActiveAccount(UserAccount activeAccount);
    void startAutoUpdate(UserAccount activeAccount);
    void startDemoMode();
    void stopAutoUpdate();
    BehaviorSubject<Boolean> getAccountStatus();
    String getApiKey();
}
