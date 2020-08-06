package com.platdmit.mod_servers.domain.repositories

import com.platdmit.mod_servers.domain.models.Statistic
import io.reactivex.rxjava3.core.Observable

interface ServerStatisticsRepo {
    fun getServerStatistics(id: Long): Observable<List<Statistic>>
}