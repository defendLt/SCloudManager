package com.platdmit.simplecloudmanager.domain.converters

import com.platdmit.simplecloudmanager.data.api.models.ApiLoadAverage
import com.platdmit.simplecloudmanager.data.database.entity.DbLoadAverage
import com.platdmit.simplecloudmanager.domain.models.LoadAverage

interface LoadAverageConverter {
    fun fromApiToDb(apiLoadAverage: ApiLoadAverage, serverId: Long): List<DbLoadAverage>
    fun fromApiToDomain(apiLoadAverage: ApiLoadAverage): List<LoadAverage>
    fun fromDbToDomain(dbLoadAverages: List<DbLoadAverage>): List<LoadAverage>
}