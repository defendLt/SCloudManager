package com.platdmit.mod_domains.domain.repositories

import com.platdmit.mod_domains.domain.models.Domain
import io.reactivex.rxjava3.core.Observable

interface DomainBaseRepo {
    fun getDomains(): Observable<List<com.platdmit.mod_domains.domain.models.Domain>>
    fun getDomain(id: Long): Observable<com.platdmit.mod_domains.domain.models.Domain>
    fun nextUpdate()
}