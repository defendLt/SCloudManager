package com.platdmit.feature_servers.domain.repositories

import com.platdmit.feature_servers.domain.models.Server
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface ServerBaseRepo {
    fun getServers(): Observable<List<_root_ide_package_.com.platdmit.feature_servers.domain.models.Server>>
    fun getServer(id: Long): Single<_root_ide_package_.com.platdmit.feature_servers.domain.models.Server>
    fun nextUpdate()
}