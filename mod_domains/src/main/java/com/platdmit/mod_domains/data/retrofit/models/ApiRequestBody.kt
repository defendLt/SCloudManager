package com.platdmit.mod_domains.data.retrofit.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiRequestBody(
        @SerializedName("domain")
        @Expose
        val domain: ApiDomain?,

        @SerializedName("domains")
        @Expose
        val domains: List<ApiDomain>?,

        @SerializedName("domain_record")
        @Expose
        val domainRecord: ApiDomainRecord?,

        @SerializedName("domain_records")
        @Expose
        val domainRecords: List<ApiDomainRecord>?
)