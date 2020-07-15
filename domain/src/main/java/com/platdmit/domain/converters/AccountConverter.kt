package com.platdmit.domain.converters

interface AccountConverter <ApiModel, DomainModel, DbModel> {
    fun fromApiToDb(apiAccount: ApiModel, password: String): DbModel
    fun fromApiToDomain(apiAccount: ApiModel, password: String): DomainModel
    fun fromDbToDomain(dbAccount: DbModel): DomainModel
    fun fromDomainToDb(userAccount: DomainModel): DbModel
}