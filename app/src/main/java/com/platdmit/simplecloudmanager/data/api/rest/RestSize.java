package com.platdmit.simplecloudmanager.data.api.rest;

import com.platdmit.simplecloudmanager.data.api.models.ApiRequestBody;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestSize {

    @GET("sizes")
    public Call<ApiRequestBody> getSizes();
}
