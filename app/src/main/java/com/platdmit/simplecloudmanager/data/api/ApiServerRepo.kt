package com.platdmit.simplecloudmanager.data.api

import com.platdmit.simplecloudmanager.data.api.models.*
import io.reactivex.rxjava3.core.Single

interface ApiServerRepo {
    fun getServers(): Single<List<ApiServer>>
    fun getServerActions(serverId: Long): Single<List<ApiAction>>
    fun getServerStatistics(serverId: Long): Single<List<ApiStatistic>>
    fun getServerBackups(serverId: Long): Single<List<ApiBackup>>
    fun getServerLoadAverage(serverId: Long): Single<ApiLoadAverage>
}