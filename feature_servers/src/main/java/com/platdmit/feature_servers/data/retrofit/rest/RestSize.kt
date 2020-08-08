package com.platdmit.feature_servers.data.retrofit.rest

import com.platdmit.feature_servers.data.retrofit.models.ApiRequestBody
import retrofit2.Call
import retrofit2.http.GET

interface RestSize {
    @GET("sizes")
    fun getSizes(): Call<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiRequestBody>
}