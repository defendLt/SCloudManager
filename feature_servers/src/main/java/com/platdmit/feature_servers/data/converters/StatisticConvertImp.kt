package com.platdmit.feature_servers.data.converters

import com.platdmit.feature_servers.domain.converters.StatisticConverter
import com.platdmit.feature_servers.domain.models.Statistic
import com.platdmit.feature_servers.data.retrofit.models.ApiStatistic
import com.platdmit.feature_servers.data.room.entity.DbStatistic
import org.joda.time.format.DateTimeFormat
import javax.inject.Inject

class StatisticConvertImp
@Inject
constructor() : _root_ide_package_.com.platdmit.feature_servers.domain.converters.StatisticConverter<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiStatistic, _root_ide_package_.com.platdmit.feature_servers.domain.models.Statistic, _root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbStatistic> {
    override fun fromApiToDb(apiStatistic: _root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiStatistic, serverId: Long): _root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbStatistic {
        return _root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbStatistic(
                serverId,
                timeConvert(apiStatistic.time), apiStatistic.ram.toFloat(), apiStatistic.cpu.toFloat())
    }

    override fun fromDbToDomain(dbStatistic: _root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbStatistic): _root_ide_package_.com.platdmit.feature_servers.domain.models.Statistic {
        return _root_ide_package_.com.platdmit.feature_servers.domain.models.Statistic(
                dbStatistic.time,
                dbStatistic.ramVal,
                dbStatistic.cpuVal
        )
    }

    override fun fromApiToDbList(apiStatistics: List<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiStatistic>, serverId: Long): List<_root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbStatistic> = apiStatistics.map { fromApiToDb(it, serverId) }

    override fun fromDbToDomainList(dbStatistics: List<_root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbStatistic>): List<_root_ide_package_.com.platdmit.feature_servers.domain.models.Statistic> = dbStatistics.map { fromDbToDomain(it) }

    private fun timeConvert(date: String): String {
        return try {
            val dateTimeFormatter = DateTimeFormat.forPattern("DD.MM.yyyy HH:mm:SS")
            DateTimeFormat.forPattern("HH:mm").print(dateTimeFormatter.parseDateTime(date))
        } catch (ignored: NullPointerException) {
            ""
        } catch (ignored: IllegalArgumentException) {
            ""
        }
    }
}