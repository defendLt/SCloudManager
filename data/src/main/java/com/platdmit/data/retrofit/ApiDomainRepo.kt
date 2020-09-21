package com.platdmit.data.retrofit

import io.reactivex.rxjava3.core.Single

interface ApiDomainRepo<Domain, Record> {
    fun getDomains(): Single<List<Domain>>
    fun getDomain(domainId: Long): Single<Domain>
    fun getDomainRecords(domainId: Long): Single<List<Record>>
    fun getDomainRecord(domainId: Int, recordId: Int): Single<Record>
}