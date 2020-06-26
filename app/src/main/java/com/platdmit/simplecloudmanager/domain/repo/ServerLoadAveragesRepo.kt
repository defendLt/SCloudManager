package com.platdmit.simplecloudmanager.domain.repo

import com.platdmit.simplecloudmanager.domain.models.LoadAverage
import io.reactivex.rxjava3.core.Observable

interface ServerLoadAveragesRepo {
    fun getServerLoadAverages(id: Long): Observable<List<LoadAverage>>
}