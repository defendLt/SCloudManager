package com.platdmit.simplecloudmanager.domain.converters

import com.platdmit.simplecloudmanager.data.api.models.ApiDomainRecord
import com.platdmit.simplecloudmanager.data.database.entity.DbDomainRecord
import com.platdmit.simplecloudmanager.domain.models.DomainRecord

interface DomainRecordConverter {
    fun fromApiToDb(apiDomainRecord: ApiDomainRecord, domainId: Long): DbDomainRecord
    fun fromDbToDomain(dbDomainRecord: DbDomainRecord): DomainRecord
    fun fromDbToDomainList(dbList: List<DbDomainRecord>): List<DomainRecord>
    fun fromApiToDbList(apiList: List<ApiDomainRecord>, domainId: Long): List<DbDomainRecord>
}