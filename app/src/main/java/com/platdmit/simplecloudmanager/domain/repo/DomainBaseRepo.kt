package com.platdmit.simplecloudmanager.domain.repo

import com.platdmit.simplecloudmanager.domain.models.Domain
import io.reactivex.rxjava3.core.Observable

interface DomainBaseRepo {
    fun getDomains(): Observable<List<Domain>>
    fun getDomain(id: Long): Observable<Domain>
    fun nextUpdate()
}