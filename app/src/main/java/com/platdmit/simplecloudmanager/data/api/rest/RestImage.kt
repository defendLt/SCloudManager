package com.platdmit.simplecloudmanager.data.api.rest

import com.platdmit.simplecloudmanager.data.api.models.ApiRequestBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RestImage {
    @GET("images")
    fun getImages(): Call<ApiRequestBody>

    @GET("images/{id}")
    fun getImage(@Path("id") id: Int): Call<ApiRequestBody>
}