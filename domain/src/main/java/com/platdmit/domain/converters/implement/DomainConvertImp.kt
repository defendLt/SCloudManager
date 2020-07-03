package com.platdmit.domain.converters.implement

import com.platdmit.data.api.models.ApiDomain
import com.platdmit.data.database.entity.DbDomain
import com.platdmit.domain.converters.DomainConverter
import com.platdmit.domain.models.Domain

class DomainConvertImp : DomainConverter {
    override fun fromApiToDb(apiDomain: ApiDomain): DbDomain {
        return DbDomain(apiDomain.id, apiDomain.name, apiDomain.type, apiDomain.deleteDate, apiDomain.isDelegated)
    }

    override fun fromDbToDomain(dbDomain: DbDomain): Domain {
        return Domain(dbDomain.id, dbDomain.name, dbDomain.type, dbDomain.deleteDate, dbDomain.isDelegate)
    }

    override fun fromApiToDomain(apiDomain: ApiDomain): Domain {
        return Domain(apiDomain.id, apiDomain.name, apiDomain.type, apiDomain.deleteDate, apiDomain.isDelegated)
    }

    override fun fromApiToDomainList(apiList: List<ApiDomain>): List<Domain> = apiList.map { fromApiToDomain(it) }

    override fun fromDbToDomainList(dbList: List<DbDomain>): List<Domain> = dbList.map { fromDbToDomain(it) }

    override fun fromApiToDbList(apiList: List<ApiDomain>): List<DbDomain> = apiList.map { fromApiToDb(it) }
}