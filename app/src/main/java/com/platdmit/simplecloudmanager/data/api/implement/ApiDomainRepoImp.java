package com.platdmit.simplecloudmanager.data.api.implement;

import com.platdmit.simplecloudmanager.data.api.ApiDomainRepo;
import com.platdmit.simplecloudmanager.data.api.ApiManager;
import com.platdmit.simplecloudmanager.data.api.models.ApiDomain;
import com.platdmit.simplecloudmanager.data.api.models.ApiDomainRecord;
import com.platdmit.simplecloudmanager.data.api.models.ApiRequestBody;
import com.platdmit.simplecloudmanager.data.api.rest.RestDomain;
import com.platdmit.simplecloudmanager.domain.helpers.ActualApiKeyService;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;

public class ApiDomainRepoImp implements ApiDomainRepo {
    private static final String TAG = ApiDomainRepoImp.class.getSimpleName();
    private ActualApiKeyService mActualApiKeyService;

    public ApiDomainRepoImp(ActualApiKeyService actualApiKeyService) {
        mActualApiKeyService = actualApiKeyService;
    }

    private RestDomain getRestDomain(){
        //always gets the actual key
        return ApiManager.<RestDomain>getApiPoint(RestDomain.class, mActualApiKeyService.getApiKey());
    }

    @Override
    public Single<List<ApiDomain>> getDomains() {
        return Single.create(requestResult -> {
            try {
                Response<ApiRequestBody> response = getRestDomain().getDomains().execute();
                if (response.isSuccessful()){
                    ApiRequestBody apiRequestBody = response.body();
                    requestResult.onSuccess(apiRequestBody.getDomains());
                } else {
                    throw new Throwable(response.message());
                }
            } catch (Exception e) {
                requestResult.onError(e);
            }
        });
    }

    @Override
    public Single<ApiDomain> getDomain(long domainId) {
        return Single.create(requestResult -> {
            try {
                Response<ApiRequestBody> response = getRestDomain().getDomain(domainId).execute();
                if (response.isSuccessful()){
                    ApiRequestBody apiRequestBody = response.body();
                    requestResult.onSuccess(apiRequestBody.getDomain());
                } else {
                    throw new Throwable(response.message());
                }
            } catch (Exception e) {
                requestResult.onError(e);
            }
        });
    }

    @Override
    public Single<List<ApiDomainRecord>> getDomainRecords(long domainId) {
        return Single.create(requestResult -> {
            try {
                Response<ApiRequestBody> response = getRestDomain().getDomainRecords(domainId).execute();
                if (response.isSuccessful()){
                    ApiRequestBody apiRequestBody = response.body();
                    requestResult.onSuccess(apiRequestBody.getDomainRecords());
                } else {
                    throw new Throwable(response.message());
                }
            } catch (Exception e) {
                requestResult.onError(e);
            }
        });
    }

    @Override
    public Single<ApiDomainRecord> getDomainRecord(int domainId, int recordId) {
        return Single.create(requestResult -> {
            try {
                Response<ApiRequestBody> response = getRestDomain().getDomain(domainId).execute();
                if (response.isSuccessful()){
                    ApiRequestBody apiRequestBody = response.body();
                    requestResult.onSuccess(apiRequestBody.getDomainRecord());
                } else {
                    throw new Throwable(response.message());
                }
            } catch (Exception e) {
                requestResult.onError(e);
            }
        });
    }
}
