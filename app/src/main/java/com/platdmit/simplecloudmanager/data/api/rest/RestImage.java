package com.platdmit.simplecloudmanager.data.api.rest;

import com.platdmit.simplecloudmanager.data.api.models.ApiRequestBody;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestImage {
    @GET("images")
    public Call<ApiRequestBody> getImages();

    @GET("images/{id}")
    public Call<ApiRequestBody> getImage(@Path("id") int id);
}
