package com.platdmit.data.api.rest

import com.platdmit.data.api.models.ApiRequestBody
import retrofit2.Call
import retrofit2.http.GET

interface RestSize {
    @GET("sizes")
    fun getSizes(): Call<ApiRequestBody>
}