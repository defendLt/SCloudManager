package com.platdmit.domain.converters

import com.platdmit.data.api.models.ApiStatistic
import com.platdmit.data.database.entity.DbStatistic
import com.platdmit.domain.models.Statistic

interface StatisticConverter {
    fun fromApiToDb(apiStatistic: ApiStatistic, serverId: Long): DbStatistic
    fun fromDbToDomain(dbStatistic: DbStatistic): Statistic
    fun fromApiToDbList(apiStatistics: List<ApiStatistic>, serverId: Long): List<DbStatistic>
    fun fromDbToDomainList(dbStatistics: List<DbStatistic>): List<Statistic>
}