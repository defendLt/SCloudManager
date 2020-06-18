package com.platdmit.simplecloudmanager.domain.converters

import com.platdmit.simplecloudmanager.data.api.models.ApiDomain
import com.platdmit.simplecloudmanager.data.database.entity.DbDomain
import com.platdmit.simplecloudmanager.domain.models.Domain

interface DomainConverter {
    fun fromApiToDb(apiDomain: ApiDomain): DbDomain
    fun fromDbToDomain(dbDomain: DbDomain): Domain
    fun fromApiToDomain(apiDomain: ApiDomain): Domain
    fun fromApiToDomainList(apiList: List<ApiDomain>): List<Domain>
    fun fromDbToDomainList(dbList: List<DbDomain>): List<Domain>
    fun fromApiToDbList(apiList: List<ApiDomain>): List<DbDomain>
}