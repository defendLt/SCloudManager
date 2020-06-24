package com.platdmit.simplecloudmanager.domain.converters.implement

import com.platdmit.simplecloudmanager.data.api.models.ApiDomainRecord
import com.platdmit.simplecloudmanager.data.database.entity.DbDomainRecord
import com.platdmit.simplecloudmanager.domain.converters.DomainRecordConverter
import com.platdmit.simplecloudmanager.domain.models.DomainRecord
import java.util.*

class DomainRecordConvertImp : DomainRecordConverter {
    override fun fromApiToDb(apiDomainRecord: ApiDomainRecord, domainId: Long): DbDomainRecord {
        return DbDomainRecord(
                apiDomainRecord.id, apiDomainRecord.priority.toString(), apiDomainRecord.port.toString(), apiDomainRecord.weight.toString(), apiDomainRecord.ttl.toString(),
                apiDomainRecord.type,
                apiDomainRecord.name,
                apiDomainRecord.data,
                domainId
        )
    }

    override fun fromDbToDomain(dbDomainRecord: DbDomainRecord): DomainRecord {
        return DomainRecord(
                dbDomainRecord.id,
                dbDomainRecord.priority,
                dbDomainRecord.port,
                dbDomainRecord.weight,
                dbDomainRecord.ttl,
                dbDomainRecord.type,
                dbDomainRecord.name,
                dbDomainRecord.data
        )
    }

    override fun fromDbToDomainList(dbList: List<DbDomainRecord>): List<DomainRecord> = dbList.map { fromDbToDomain(it) }

    override fun fromApiToDbList(apiList: List<ApiDomainRecord>, domainId: Long): List<DbDomainRecord> = apiList.map { fromApiToDb(it, domainId) }
}