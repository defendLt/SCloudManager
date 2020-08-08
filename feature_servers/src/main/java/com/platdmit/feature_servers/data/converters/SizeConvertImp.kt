package com.platdmit.feature_servers.data.converters

import com.platdmit.feature_servers.domain.converters.SizeConverter
import com.platdmit.feature_servers.domain.models.Size
import com.platdmit.feature_servers.data.retrofit.models.ApiSize
import com.platdmit.feature_servers.data.room.entity.DbSize
import javax.inject.Inject

class SizeConvertImp
@Inject
constructor() : _root_ide_package_.com.platdmit.feature_servers.domain.converters.SizeConverter<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiSize, _root_ide_package_.com.platdmit.feature_servers.domain.models.Size, _root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbSize> {
    override fun fromApiToDb(apiSize: _root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiSize): _root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbSize {
        return _root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbSize(
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

    override fun fromDbToDomain(dbSize: _root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbSize): _root_ide_package_.com.platdmit.feature_servers.domain.models.Size {
        return _root_ide_package_.com.platdmit.feature_servers.domain.models.Size(
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

    override fun fromDbToDomainList(dbList: List<_root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbSize>): List<_root_ide_package_.com.platdmit.feature_servers.domain.models.Size> = dbList.map { fromDbToDomain(it) }

    override fun fromApiToDbList(apiList: List<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiSize>): List<_root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbSize> = apiList.map { fromApiToDb(it) }
}