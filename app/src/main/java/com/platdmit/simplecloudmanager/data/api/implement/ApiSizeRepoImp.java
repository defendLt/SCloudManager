package com.platdmit.simplecloudmanager.data.api.implement;

import com.platdmit.simplecloudmanager.data.api.ApiManager;
import com.platdmit.simplecloudmanager.data.api.ApiSizeRepo;
import com.platdmit.simplecloudmanager.data.api.models.ApiRequestBody;
import com.platdmit.simplecloudmanager.data.api.models.ApiSize;
import com.platdmit.simplecloudmanager.data.api.rest.RestSize;
import com.platdmit.simplecloudmanager.domain.helpers.ActualApiKeyService;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;

public class ApiSizeRepoImp implements ApiSizeRepo {
    private static final String TAG = ApiSizeRepoImp.class.getSimpleName();
    private ActualApiKeyService mActualApiKeyService;

    public ApiSizeRepoImp(ActualApiKeyService actualApiKeyService) {
        mActualApiKeyService = actualApiKeyService;
    }

    private RestSize getRestSize(){
        //always gets the actual key
        return ApiManager.<RestSize>getApiPoint(RestSize.class, mActualApiKeyService.getApiKey());
    }

    @Override
    public Single<List<ApiSize>> getSizes() {
        return Single.create(requestResult -> {
            try {
                Response<ApiRequestBody> response = getRestSize().getSizes().execute();
                if (response.isSuccessful()){
                    ApiRequestBody apiRequestBody = response.body();
                    requestResult.onSuccess(apiRequestBody.getSizes());
                } else {
                    throw new Throwable(response.message());
                }
            } catch (Exception e) {
                requestResult.onError(e);
            }
        });
    }
}
