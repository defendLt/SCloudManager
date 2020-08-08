package com.platdmit.feature_servers.domain.repositories

import com.platdmit.feature_servers.domain.models.Statistic
import io.reactivex.rxjava3.core.Observable

interface ServerStatisticsRepo {
    fun getServerStatistics(id: Long): Observable<List<_root_ide_package_.com.platdmit.feature_servers.domain.models.Statistic>>
}