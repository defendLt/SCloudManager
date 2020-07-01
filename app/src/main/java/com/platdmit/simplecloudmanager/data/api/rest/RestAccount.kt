package com.platdmit.simplecloudmanager.data.api.rest

import com.platdmit.simplecloudmanager.data.api.models.ApiAuth
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface RestAccount {
    @POST("account")
    fun getAccount() : Call<ApiAuth>

    @POST("auth/login")
    fun getApiKey(@Query("login") login: String, @Query("password") password: String): Call<ApiAuth>
}