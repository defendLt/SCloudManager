package com.platdmit.domain.repositories

import com.platdmit.domain.models.Domain
import io.reactivex.rxjava3.core.Observable

interface DomainBaseRepo {
    fun getDomains(): Observable<List<Domain>>
    fun getDomain(id: Long): Observable<Domain>
    fun nextUpdate()
}