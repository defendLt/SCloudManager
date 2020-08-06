package com.platdmit.mod_servers.domain.converters

interface LoadAverageConverter <ApiModel, DomainModel, DbModel> {
    fun fromApiToDb(apiLoadAverage: ApiModel, serverId: Long): List<DbModel>
    fun fromApiToDomain(apiLoadAverage: ApiModel): List<DomainModel>
    fun fromDbToDomain(dbLoadAverages: List<DbModel>): List<DomainModel>
}