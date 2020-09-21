package com.platdmit.data.retrofit

import io.reactivex.rxjava3.core.Single

interface ApiServerRepo<Server, Action, Stat, Backup, LoadAverage> {
    fun getServers(): Single<List<Server>>
    fun getServerActions(serverId: Long): Single<List<Action>>
    fun getServerStatistics(serverId: Long): Single<List<Stat>>
    fun getServerBackups(serverId: Long): Single<List<Backup>>
    fun getServerLoadAverage(serverId: Long): Single<LoadAverage>
}