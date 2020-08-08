package com.platdmit.feature_domains.domain.converters

interface DomainRecordConverter <ApiModel, DomainModel, DbModel> {
    fun fromApiToDb(apiDomainRecord: ApiModel, domainId: Long): DbModel
    fun fromDbToDomain(dbDomainRecord: DbModel): DomainModel
    fun fromDbToDomainList(dbList: List<DbModel>): List<DomainModel>
    fun fromApiToDbList(apiList: List<ApiModel>, domainId: Long): List<DbModel>
}