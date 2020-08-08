package com.platdmit.feature_servers.domain.converters

interface BackupConverter <ApiModel, DomainModel, DbModel> {
    fun fromApiToDb(apiBackup: ApiModel, serverId: Long): DbModel
    fun fromDbToDomain(dbBackup: DbModel): DomainModel
    fun fromDbToDomainList(dbList: List<DbModel>): List<DomainModel>
    fun fromApiToDbList(apiList: List<ApiModel>, serverId: Long): List<DbModel>
}