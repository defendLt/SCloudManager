package com.platdmit.mod_servers.domain.repositories

import com.platdmit.mod_servers.domain.models.LoadAverage
import io.reactivex.rxjava3.core.Observable

interface ServerLoadAveragesRepo {
    fun getServerLoadAverages(id: Long): Observable<List<LoadAverage>>
}