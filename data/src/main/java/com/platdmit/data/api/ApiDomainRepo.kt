package com.platdmit.data.api

import com.platdmit.data.api.models.ApiDomain
import com.platdmit.data.api.models.ApiDomainRecord
import io.reactivex.rxjava3.core.Single

interface ApiDomainRepo {
    fun getDomains(): Single<List<ApiDomain>>
    fun getDomain(domainId: Long): Single<ApiDomain>
    fun getDomainRecords(domainId: Long): Single<List<ApiDomainRecord>>
    fun getDomainRecord(domainId: Int, recordId: Int): Single<ApiDomainRecord>
}