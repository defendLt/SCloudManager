package com.platdmit.domain.converters.implement

import com.platdmit.data.api.models.ApiStatistic
import com.platdmit.data.database.entity.DbStatistic
import com.platdmit.domain.converters.StatisticConverter
import com.platdmit.domain.models.Statistic
import org.joda.time.format.DateTimeFormat
import java.util.*

class StatisticConvertImp : StatisticConverter {
    override fun fromApiToDb(apiStatistic: ApiStatistic, serverId: Long): DbStatistic {
        return DbStatistic(
                serverId,
                timeConvert(apiStatistic.time), apiStatistic.ram.toFloat(), apiStatistic.cpu.toFloat())
    }

    override fun fromDbToDomain(dbStatistic: DbStatistic): Statistic {
        return Statistic(
                dbStatistic.time,
                dbStatistic.ramVal,
                dbStatistic.cpuVal
        )
    }

    override fun fromApiToDbList(apiStatistics: List<ApiStatistic>, serverId: Long): List<DbStatistic> = apiStatistics.map { fromApiToDb(it, serverId) }

    override fun fromDbToDomainList(dbStatistics: List<DbStatistic>): List<Statistic> = dbStatistics.map { fromDbToDomain(it) }

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