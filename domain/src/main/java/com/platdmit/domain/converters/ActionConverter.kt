package com.platdmit.domain.converters

interface ActionConverter <ApiModel, DomainModel, DbModel> {
    fun fromApiToDb(apiAction: ApiModel): DbModel
    fun fromDbToDomain(dbAction: DbModel): DomainModel
    fun fromDbToDomainList(dbList: List<DbModel>): List<DomainModel>
    fun fromApiToDbList(apiList: List<ApiModel>): List<DbModel>
}