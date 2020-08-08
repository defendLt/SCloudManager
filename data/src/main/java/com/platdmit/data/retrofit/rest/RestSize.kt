package com.platdmit.data.retrofit.rest

import com.platdmit.data.retrofit.models.ApiRequestBody
import retrofit2.Call
import retrofit2.http.GET

interface RestSize {
    @GET("sizes")
    fun getSizes(): Call<ApiRequestBody>
}