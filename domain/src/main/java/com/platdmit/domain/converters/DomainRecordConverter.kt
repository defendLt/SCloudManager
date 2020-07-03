package com.platdmit.domain.converters

import com.platdmit.data.api.models.ApiDomainRecord
import com.platdmit.data.database.entity.DbDomainRecord
import com.platdmit.domain.models.DomainRecord

interface DomainRecordConverter {
    fun fromApiToDb(apiDomainRecord: ApiDomainRecord, domainId: Long): DbDomainRecord
    fun fromDbToDomain(dbDomainRecord: DbDomainRecord): DomainRecord
    fun fromDbToDomainList(dbList: List<DbDomainRecord>): List<DomainRecord>
    fun fromApiToDbList(apiList: List<ApiDomainRecord>, domainId: Long): List<DbDomainRecord>
}