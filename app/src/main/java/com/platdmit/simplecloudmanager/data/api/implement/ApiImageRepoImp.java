package com.platdmit.simplecloudmanager.data.api.implement;

import com.platdmit.simplecloudmanager.data.api.ApiImageRepo;
import com.platdmit.simplecloudmanager.data.api.ApiManager;
import com.platdmit.simplecloudmanager.data.api.models.ApiImage;
import com.platdmit.simplecloudmanager.data.api.models.ApiRequestBody;
import com.platdmit.simplecloudmanager.data.api.rest.RestImage;
import com.platdmit.simplecloudmanager.domain.helpers.ActualApiKeyService;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;

public class ApiImageRepoImp implements ApiImageRepo {
    private static final String TAG = ApiDomainRepoImp.class.getSimpleName();
    private ActualApiKeyService mActualApiKeyService;

    public ApiImageRepoImp(ActualApiKeyService actualApiKeyService) {
        mActualApiKeyService = actualApiKeyService;
    }

    private RestImage getRestImage(){
        //always gets the actual key
        return ApiManager.<RestImage>getApiPoint(RestImage.class, mActualApiKeyService.getApiKey());
    }

    @Override
    public Single<List<ApiImage>> getImages() {
        return Single.create(requestResult -> {
            try {
                Response<ApiRequestBody> response = getRestImage().getImages().execute();
                if (response.isSuccessful()){
                    ApiRequestBody apiRequestBody = response.body();
                    requestResult.onSuccess(apiRequestBody.getImages());
                } else {
                    throw new Throwable(response.message());
                }
            } catch (Exception e) {
                requestResult.onError(e);
            }
        });
    }
}
