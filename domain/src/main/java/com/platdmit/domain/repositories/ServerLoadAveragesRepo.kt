package com.platdmit.domain.repositories

import com.platdmit.domain.models.LoadAverage
import io.reactivex.rxjava3.core.Observable

interface ServerLoadAveragesRepo {
    fun getServerLoadAverages(id: Long): Observable<List<LoadAverage>>
}