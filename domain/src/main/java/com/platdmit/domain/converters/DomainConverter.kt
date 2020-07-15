package com.platdmit.domain.converters

interface DomainConverter <ApiModel, DomainModel, DbModel> {
    fun fromApiToDb(apiDomain: ApiModel): DbModel
    fun fromDbToDomain(dbDomain: DbModel): DomainModel
    fun fromApiToDomain(apiDomain: ApiModel): DomainModel
    fun fromApiToDomainList(apiList: List<ApiModel>): List<DomainModel>
    fun fromDbToDomainList(dbList: List<DbModel>): List<DomainModel>
    fun fromApiToDbList(apiList: List<ApiModel>): List<DbModel>
}