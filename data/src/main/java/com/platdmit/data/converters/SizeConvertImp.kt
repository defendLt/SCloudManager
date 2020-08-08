package com.platdmit.data.converters

import com.platdmit.data.retrofit.models.ApiSize
import com.platdmit.data.room.entity.DbSize
import com.platdmit.domain.converters.SizeConverter
import com.platdmit.domain.models.Size
import javax.inject.Inject

class SizeConvertImp
@Inject
constructor() : SizeConverter<ApiSize, Size, DbSize> {
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
                apiSize.test,
                apiSize.archive
        )
    }

    override fun fromDbToDomain(dbSize: DbSize): Size {
        return Size(
                dbSize.id,
                "${dbSize.id}",
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