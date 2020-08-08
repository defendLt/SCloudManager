package com.platdmit.feature_servers.data.retrofit.rest

import com.platdmit.feature_servers.data.retrofit.models.ApiRequestBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RestImage {
    @GET("images")
    fun getImages(): Call<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiRequestBody>

    @GET("images/{id}")
    fun getImage(@Path("id") id: Int): Call<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiRequestBody>
}