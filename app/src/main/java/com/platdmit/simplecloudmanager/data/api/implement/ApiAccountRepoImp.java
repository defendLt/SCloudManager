package com.platdmit.simplecloudmanager.data.api.implement;

import com.platdmit.simplecloudmanager.data.api.ApiAccountRepo;
import com.platdmit.simplecloudmanager.data.api.ApiManager;
import com.platdmit.simplecloudmanager.data.api.models.ApiAccount;
import com.platdmit.simplecloudmanager.data.api.models.ApiAuth;
import com.platdmit.simplecloudmanager.data.api.models.ApiRequestBody;
import com.platdmit.simplecloudmanager.data.api.rest.RestAccount;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;

public class ApiAccountRepoImp implements ApiAccountRepo {
    private static final String TAG = ApiAccountRepoImp.class.getSimpleName();
    private RestAccount mSRestAccount;

    public ApiAccountRepoImp() {
        mSRestAccount = ApiManager.<RestAccount>getApiPoint(RestAccount.class, "");
    }

    @Override
    public Single<ApiAuth> getApiKey(String login, String pass) {
        return Single.create(requestResult -> {
            try {
                Response<ApiAuth> response = mSRestAccount.getApiKey(login, pass).execute();
                if (response.isSuccessful()){
                    requestResult.onSuccess(response.body());
                } else {
                    throw new Throwable(response.message());
                }
            } catch (Exception e) {
                requestResult.onError(e);
            }
        });
    }

    @Override
    public Single<ApiAccount> getActiveAccount() {
        return Single.create(requestResult -> {
            try {
                Response<ApiAuth> response = mSRestAccount.getAccount().execute();
                if (response.isSuccessful()){
                    ApiRequestBody apiRequestBody = response.body().getAccount();
                    requestResult.onSuccess(apiRequestBody.getAccount());
                } else {
                    throw new Throwable(response.message());
                }
            } catch (Exception e) {
                requestResult.onError(e);
            }
        });
    }
}
