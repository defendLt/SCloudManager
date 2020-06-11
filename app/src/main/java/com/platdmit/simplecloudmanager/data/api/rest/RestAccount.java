package com.platdmit.simplecloudmanager.data.api.rest;

import com.platdmit.simplecloudmanager.data.api.models.ApiAuth;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestAccount {

    @POST("account")
    Call<ApiAuth> getAccount();

    @POST("auth/login")
    Call<ApiAuth> getApiKey(@Query("login") String login, @Query("password") String password);
}
