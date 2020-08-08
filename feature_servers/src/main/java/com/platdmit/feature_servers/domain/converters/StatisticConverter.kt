package com.platdmit.feature_servers.domain.converters

interface StatisticConverter <ApiModel, DomainModel, DbModel> {
    fun fromApiToDb(apiStatistic: ApiModel, serverId: Long): DbModel
    fun fromDbToDomain(dbStatistic: DbModel): DomainModel
    fun fromApiToDbList(apiStatistics: List<ApiModel>, serverId: Long): List<DbModel>
    fun fromDbToDomainList(dbStatistics: List<DbModel>): List<DomainModel>
}