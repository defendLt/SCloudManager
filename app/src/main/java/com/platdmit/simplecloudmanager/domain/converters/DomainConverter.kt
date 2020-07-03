package com.platdmit.simplecloudmanager.domain.converters

import com.platdmit.data.api.models.ApiDomain
import com.platdmit.data.database.entity.DbDomain
import com.platdmit.simplecloudmanager.domain.models.Domain

interface DomainConverter {
    fun fromApiToDb(apiDomain: ApiDomain): DbDomain
    fun fromDbToDomain(dbDomain: DbDomain): Domain
    fun fromApiToDomain(apiDomain: ApiDomain): Domain
    fun fromApiToDomainList(apiList: List<ApiDomain>): List<Domain>
    fun fromDbToDomainList(dbList: List<DbDomain>): List<Domain>
    fun fromApiToDbList(apiList: List<ApiDomain>): List<DbDomain>
}