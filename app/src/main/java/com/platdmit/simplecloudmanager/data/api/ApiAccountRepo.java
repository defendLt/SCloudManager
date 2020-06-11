package com.platdmit.simplecloudmanager.data.api;

import com.platdmit.simplecloudmanager.data.api.models.ApiAccount;
import com.platdmit.simplecloudmanager.data.api.models.ApiAuth;
import com.platdmit.simplecloudmanager.data.api.models.ApiRequestResult;

import io.reactivex.rxjava3.core.Single;

public interface ApiAccountRepo {
    Single<ApiAuth> getApiKey(String login, String pass);
    Single<ApiAccount> getActiveAccount();
}
