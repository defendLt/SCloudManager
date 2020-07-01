package com.platdmit.simplecloudmanager.data.api.rest

import com.platdmit.simplecloudmanager.data.api.models.ApiDomain
import com.platdmit.simplecloudmanager.data.api.models.ApiDomainRecord
import com.platdmit.simplecloudmanager.data.api.models.ApiRequestBody
import retrofit2.Call
import retrofit2.http.*

interface RestDomain {
    @GET("domains")
    fun getDomains(): Call<ApiRequestBody>

    @GET("domains/{id}")
    fun getDomain(@Path("id") id: Long): Call<ApiRequestBody>

    @POST("domains")
    fun addDomain(@Query("name") name: String, @Query("ip_address") ip_address: String): Call<ApiDomain>

    @DELETE("domains/{id}")
    fun dellDomain(@Path("id") id: Long)

    @GET("domains/{domainId}/records")
    fun getDomainRecords(@Path("domainId") domainId: Long): Call<ApiRequestBody>

    @GET("domains/{domainId}/records/{recordId}")
    fun getDomainRecord(@Path("domainId") domainId: Int, @Path("recordId") recordId: Int): Call<ApiRequestBody>

    @POST("domains/{domainId}/records")
    fun addDomainRecord(@Path("domainId") domainId: Int, @Query("name") name: String, @Query("type") type: String, @Query("data") data: String): Call<ApiDomainRecord>

    @DELETE("domains/{domainId}/records/{recordId}")
    fun dellDomainRecord(@Path("domainId") domainId: Int, @Path("recordId") recordId: Int)
}