package com.platdmit.mod_servers.domain.converters

interface ServerConverter <ApiModel, DomainModel, DbModel> {
    fun fromApiToDb(apiServer: ApiModel): DbModel
    fun fromDbToDomain(dbServer: DbModel): DomainModel
    fun fromDbToDomainFull(dbServer: DbModel): DomainModel
    fun fromDbToDomainList(dbList: List<DbModel>): List<DomainModel>
    fun fromApiToDbList(apiList: List<ApiModel>): List<DbModel>
}