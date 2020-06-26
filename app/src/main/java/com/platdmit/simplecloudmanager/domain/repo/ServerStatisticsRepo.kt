package com.platdmit.simplecloudmanager.domain.repo

import com.platdmit.simplecloudmanager.domain.models.Statistic
import io.reactivex.rxjava3.core.Observable

interface ServerStatisticsRepo {
    fun getServerStatistics(id: Long): Observable<List<Statistic>>
}