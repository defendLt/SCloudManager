package com.platdmit.simplecloudmanager.domain.converters

import com.platdmit.simplecloudmanager.data.api.models.ApiSize
import com.platdmit.simplecloudmanager.data.database.entity.DbSize
import com.platdmit.simplecloudmanager.domain.models.Size

interface SizeConverter {
    fun fromApiToDb(apiSize: ApiSize): DbSize
    fun fromDbToDomain(dbSize: DbSize): Size
    fun fromDbToDomainList(dbList: List<DbSize>): List<Size>
    fun fromApiToDbList(apiList: List<ApiSize>): List<DbSize>
}