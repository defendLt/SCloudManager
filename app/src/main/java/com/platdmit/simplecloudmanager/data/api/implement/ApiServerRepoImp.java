package com.platdmit.simplecloudmanager.data.api.implement;

import com.platdmit.simplecloudmanager.data.api.ApiManager;
import com.platdmit.simplecloudmanager.data.api.ApiServerRepo;
import com.platdmit.simplecloudmanager.data.api.models.ApiAction;
import com.platdmit.simplecloudmanager.data.api.models.ApiBackup;
import com.platdmit.simplecloudmanager.data.api.models.ApiLoadAverage;
import com.platdmit.simplecloudmanager.data.api.models.ApiRequestBody;
import com.platdmit.simplecloudmanager.data.api.models.ApiServer;
import com.platdmit.simplecloudmanager.data.api.models.ApiStatistic;
import com.platdmit.simplecloudmanager.data.api.rest.RestServer;
import com.platdmit.simplecloudmanager.domain.helpers.ActualApiKeyService;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;

public class ApiServerRepoImp implements ApiServerRepo {
    private static final String TAG = ApiServerRepoImp.class.getSimpleName();
    private ActualApiKeyService mActualApiKeyService;

    public ApiServerRepoImp(ActualApiKeyService actualApiKeyService) {
        mActualApiKeyService = actualApiKeyService;
    }

    private RestServer getRestServer(){
        //always gets the actual key
        return ApiManager.<RestServer>getApiPoint(RestServer.class, mActualApiKeyService.getApiKey());
    }

    @Override
    public Single<List<ApiServer>> getServers() {
        return Single.create(requestResult -> {
            try {
                Response<ApiRequestBody> response = getRestServer().getServers().execute();
                if (response.isSuccessful()){
                    ApiRequestBody apiRequestBody = response.body();
                    requestResult.onSuccess(apiRequestBody.getServers());
                } else {
                    throw new Throwable(response.message());
                }
            } catch (Exception e) {
                requestResult.onError(e);
            }
        });
    }

    @Override
    public Single<List<ApiAction>> getServerActions(long serverId) {
        return Single.create(requestResult -> {
            try {
                Response<ApiRequestBody> response = getRestServer().getServerActions(serverId).execute();
                if (response.isSuccessful()){
                    ApiRequestBody apiRequestBody = response.body();
                    requestResult.onSuccess(apiRequestBody.getServerActions());
                } else {
                    throw new Throwable(response.message());
                }
            } catch (Exception e) {
                requestResult.onError(e);
            }
        });
    }

    @Override
    public Single<List<ApiStatistic>> getServerStatistics(long serverId) {
        return Single.create(requestResult -> {
            try {
                Response<ApiRequestBody> response = getRestServer().getServerStatistics(serverId).execute();
                if (response.isSuccessful()){
                    ApiRequestBody apiRequestBody = response.body();
                    requestResult.onSuccess(apiRequestBody.getServerStatistics());
                } else {
                    throw new Throwable(response.message());
                }
            } catch (Exception e) {
                requestResult.onError(e);
            }
        });
    }

    @Override
    public Single<List<ApiBackup>> getServerBackups(long serverId) {
        return Single.create(requestResult -> {
            try {
                Response<ApiRequestBody> response = getRestServer().getServerBackups(serverId).execute();
                if (response.isSuccessful()) {
                    ApiRequestBody apiRequestBody = response.body();
                    requestResult.onSuccess(apiRequestBody.getServerBackups());
                } else {
                    throw new Throwable(response.message());
                }
            } catch (Exception e) {
                requestResult.onError(e);
            }
        });
    }

    @Override
    public Single<ApiLoadAverage> getServerLoadAverage(long serverId) {
        return Single.create(requestResult -> {
            try {
                Response<ApiRequestBody> response = getRestServer().getServerLoadAverage(serverId).execute();
                if (response.isSuccessful()) {
                    ApiRequestBody apiRequestBody = response.body();
                    requestResult.onSuccess(apiRequestBody.getServerLoadAverage());
                } else {
                    throw new Throwable(response.message());
                }
            } catch (Exception e) {
                requestResult.onError(e);
            }
        });
    }
}
