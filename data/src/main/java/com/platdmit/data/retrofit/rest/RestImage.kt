package com.platdmit.data.retrofit.rest

import com.platdmit.data.retrofit.models.ApiRequestBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RestImage {
    @GET("images")
    fun getImages(): Call<ApiRequestBody>

    @GET("images/{id}")
    fun getImage(@Path("id") id: Int): Call<ApiRequestBody>
}