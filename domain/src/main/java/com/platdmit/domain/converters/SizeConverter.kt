package com.platdmit.domain.converters

interface SizeConverter <ApiModel, DomainModel, DbModel> {
    fun fromApiToDb(apiSize: ApiModel): DbModel
    fun fromDbToDomain(dbSize: DbModel): DomainModel
    fun fromDbToDomainList(dbList: List<DbModel>): List<DomainModel>
    fun fromApiToDbList(apiList: List<ApiModel>): List<DbModel>
}