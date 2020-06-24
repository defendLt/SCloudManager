package com.platdmit.simplecloudmanager.domain.converters

import com.platdmit.simplecloudmanager.data.api.models.ApiStatistic
import com.platdmit.simplecloudmanager.data.database.entity.DbStatistic
import com.platdmit.simplecloudmanager.domain.models.Statistic

interface StatisticConverter {
    fun fromApiToDb(apiStatistic: ApiStatistic, serverId: Long): DbStatistic
    fun fromDbToDomain(dbStatistic: DbStatistic): Statistic
    fun fromApiToDbList(apiStatistics: List<ApiStatistic>, serverId: Long): List<DbStatistic>
    fun fromDbToDomainList(dbStatistics: List<DbStatistic>): List<Statistic>
}