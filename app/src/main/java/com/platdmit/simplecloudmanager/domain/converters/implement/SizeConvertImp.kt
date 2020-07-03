package com.platdmit.simplecloudmanager.domain.converters.implement

import com.platdmit.data.api.models.ApiSize
import com.platdmit.data.database.entity.DbSize
import com.platdmit.simplecloudmanager.domain.converters.SizeConverter
import com.platdmit.simplecloudmanager.domain.models.Size

class SizeConvertImp : SizeConverter {
    override fun fromApiToDb(apiSize: ApiSize): DbSize {
        return DbSize(
                apiSize.id.toLong(),
                apiSize.slug,
                apiSize.memory,
                apiSize.vcpus,
                apiSize.disk,
                apiSize.transfer,
                apiSize.priceMonthly,
                apiSize.priceHourly,
                apiSize.linked,
                apiSize.main,
                apiSize.isTest,
                apiSize.isArchive
        )
    }

    override fun fromDbToDomain(dbSize: DbSize): Size {
        return Size(
                dbSize.id,
                dbSize.id.toString(),
                dbSize.slug,
                dbSize.memory,
                dbSize.vcpus,
                dbSize.disk,
                dbSize.transfer,
                dbSize.priceMonthly,
                dbSize.priceHourly,
                dbSize.linked,
                dbSize.main,
                dbSize.isTest,
                dbSize.isArchive
        )
    }

    override fun fromDbToDomainList(dbList: List<DbSize>): List<Size> = dbList.map { fromDbToDomain(it) }

    override fun fromApiToDbList(apiList: List<ApiSize>): List<DbSize> = apiList.map { fromApiToDb(it) }
}