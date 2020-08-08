package com.platdmit.data.converters

import com.platdmit.data.retrofit.models.ApiDomainRecord
import com.platdmit.data.room.entity.DbDomainRecord
import com.platdmit.domain.converters.DomainRecordConverter
import com.platdmit.domain.models.DomainRecord
import javax.inject.Inject

class DomainRecordConvertImp
@Inject
constructor() : DomainRecordConverter<ApiDomainRecord, DomainRecord, DbDomainRecord> {
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