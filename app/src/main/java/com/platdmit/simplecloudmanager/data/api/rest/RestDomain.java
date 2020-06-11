package com.platdmit.simplecloudmanager.data.api.rest;

import com.platdmit.simplecloudmanager.data.api.models.ApiDomain;
import com.platdmit.simplecloudmanager.data.api.models.ApiDomainRecord;
import com.platdmit.simplecloudmanager.data.api.models.ApiRequestBody;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestDomain {

    @GET("domains")
    Call<ApiRequestBody> getDomains();

    @GET("domains/{id}")
    Call<ApiRequestBody> getDomain(@Path("id") long id);

    @POST("domains")
    Call<ApiDomain> addDomain(@Query("name") String name, @Query("ip_address") String ip_address);

    @DELETE("domains/{id}")
    void dellDomain(@Path("id") long id);

    @GET("domains/{domainId}/records")
    Call<ApiRequestBody> getDomainRecords(@Path("domainId") long domainId);

    @GET("domains/{domainId}/records/{recordId}")
    Call<ApiRequestBody> getDomainRecord(@Path("domainId") int domainId, @Path("recordId") int recordId);

    @POST("domains/{domainId}/records")
    Call<ApiDomainRecord> addDomainRecord(@Path("domainId") int domainId, @Query("name") String name, @Query("type") String type, @Query("data") String data);

    @DELETE("domains/{domainId}/records/{recordId}")
    void dellDomainRecord(@Path("domainId") int domainId, @Path("recordId") int recordId);
}
