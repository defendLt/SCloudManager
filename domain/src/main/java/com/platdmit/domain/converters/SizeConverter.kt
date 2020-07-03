package com.platdmit.domain.converters

import com.platdmit.data.api.models.ApiSize
import com.platdmit.data.database.entity.DbSize
import com.platdmit.domain.models.Size

interface SizeConverter {
    fun fromApiToDb(apiSize: ApiSize): DbSize
    fun fromDbToDomain(dbSize: DbSize): Size
    fun fromDbToDomainList(dbList: List<DbSize>): List<Size>
    fun fromApiToDbList(apiList: List<ApiSize>): List<DbSize>
}