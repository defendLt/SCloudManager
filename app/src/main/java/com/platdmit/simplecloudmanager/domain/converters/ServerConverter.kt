package com.platdmit.simplecloudmanager.domain.converters

import com.platdmit.data.api.models.ApiServer
import com.platdmit.data.database.entity.DbServer
import com.platdmit.simplecloudmanager.domain.models.Server

interface ServerConverter {
    fun fromApiToDb(apiServer: ApiServer): DbServer
    fun fromDbToDomain(dbServer: DbServer): Server
    fun fromDbToDomainFull(dbServer: DbServer): Server
    fun fromDbToDomainList(dbList: List<DbServer>): List<Server>
    fun fromApiToDbList(apiList: List<ApiServer>): List<DbServer>
}