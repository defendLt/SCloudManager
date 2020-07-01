package com.platdmit.simplecloudmanager.data.api.rest

import com.platdmit.simplecloudmanager.data.api.models.ApiRequestBody
import retrofit2.Call
import retrofit2.http.GET

interface RestSize {
    @GET("sizes")
    fun getSizes(): Call<ApiRequestBody>
}