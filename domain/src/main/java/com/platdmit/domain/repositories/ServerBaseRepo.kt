package com.platdmit.domain.repositories

import com.platdmit.domain.models.Server
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface ServerBaseRepo {
    fun getServers(): Observable<List<Server>>
    fun getServer(id: Long): Single<Server>
    fun nextUpdate()
}