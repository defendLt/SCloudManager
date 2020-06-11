package com.platdmit.simplecloudmanager.data.api.rest;

import com.platdmit.simplecloudmanager.data.api.models.ApiRequestBody;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestServer {

    @GET("vps")
    Call<ApiRequestBody> getServers();

    @GET("vps/{id}")
    Call<ApiRequestBody> getServer(@Path("id") long id);

    @GET("vps/{id}/actions")
    Call<ApiRequestBody> getServerActions(@Path("id") long id);

    @GET("vps/{id}/statistics")
    Call<ApiRequestBody> getServerStatistics(@Path("id") long id);

    @GET("vps/{id}/loadAverage")
    Call<ApiRequestBody> getServerLoadAverage(@Path("id") long id);

    @GET("vps/{id}/backups")
    Call<ApiRequestBody> getServerBackups(@Path("id") long id);

    @DELETE("vps/{id}")
    void dellServer(@Path("id") long id);
}
