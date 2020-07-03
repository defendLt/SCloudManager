package com.platdmit.simplecloudmanager.domain.converters.implement

import com.platdmit.data.api.models.ApiServer
import com.platdmit.data.database.entity.DbServer
import com.platdmit.simplecloudmanager.domain.converters.ServerConverter
import com.platdmit.simplecloudmanager.domain.models.Server
import org.joda.time.DateTime
import org.joda.time.Duration
import org.joda.time.format.DateTimeFormat
import java.util.*

class ServerConvertImp : ServerConverter {
    override fun fromApiToDb(apiServer: ApiServer): DbServer {
        return DbServer(
                apiServer.id.toLong(),
                apiServer.name,
                apiServer.memory,
                apiServer.vcpus,
                apiServer.disk,
                apiServer.region.name,
                apiServer.image.id.toLong(),
                apiServer.backupPriceHourly,
                apiServer.billing.paymentDate,
                apiServer.billing.paymentAmount,
                apiServer.billing.paymentPeriod,
                apiServer.billing.totalHours,
                apiServer.billing.workedHours,
                apiServer.isLocked,
                apiServer.status,
                apiServer.createdAt,
                apiServer.startedFirstAt,
                apiServer.startedAt,
                apiServer.isIsInstall,
                apiServer.isIsError,
                apiServer.password,
                apiServer.networks.v4.ipAddress,
                apiServer.isMbit200
        )
    }

    override fun fromDbToDomain(dbServer: DbServer): Server {
        return Server(
                dbServer.id,
                dbServer.name,
                uptimeConvert(dbServer.startedAt),
                statusConverter(dbServer.status)
        )
    }

    override fun fromDbToDomainFull(dbServer: DbServer): Server {
        return Server(
                dbServer.id,
                dbServer.name,
                dbServer.memory,
                dbServer.vcpus,
                dbServer.disk,
                dbServer.region,
                dbServer.backupPriceHourly,
                nextPaymentDateConvert(dbServer.paymentDate),
                pricePeriodConverter(dbServer.paymentAmount, dbServer.paymentPeriod),
                uptimeConvert(dbServer.startedAt),
                dbServer.isLocked,
                statusConverter(dbServer.status),
                dbServer.createdAt,
                dbServer.startedFirstAt,
                dbServer.startedAt,
                dbServer.isInstall,
                dbServer.isError,
                dbServer.password,
                dbServer.v4Ip,
                dbServer.isMbit200
        )
    }

    override fun fromDbToDomainList(dbList: List<DbServer>): List<Server> = dbList.map { fromDbToDomain(it) }

    override fun fromApiToDbList(apiList: List<ApiServer>): List<DbServer> = apiList.map { fromApiToDb(it) }

    private fun uptimeConvert(date: String): String {
        val dateTime = DateTime().minus(DateTime(date).millis)
        val uptime = Duration(dateTime.millis)
        val upTimeDay = uptime.standardDays
        val upTimeHours = dateTime.hourOfDay.toLong()
        return if (upTimeDay > 0) {
            "$UPTIME: $upTimeDay$DAY $upTimeHours$HOUR"
        } else if (upTimeHours > 0) {
            "$UPTIME: $upTimeHours$HOUR"
        } else {
            UPTIME + ":" + dateTime.minuteOfHour + MINUTE
        }
    }

    private fun nextPaymentDateConvert(date: String): String {
        return DateTimeFormat.forPattern("yyyy-MM-dd HH:mm").print(DateTime(date))
    }

    private fun pricePeriodConverter(price: String, period: String): String {
        return priceConverter(price) + "/" + periodConverter(period)
    }

    private fun priceConverter(price: String): String {
        return java.lang.Double.valueOf(price).toString() + CURRENCY_RU
    }

    private fun periodConverter(period: String): String {
        return when (period) {
            "1 HOUR" -> HOUR_RU
            "1 MONTH" -> MONTH_RU
            else -> ""
        }
    }

    private fun statusConverter(status: String): String {
        return when (status) {
            "new" -> STATUS_NEW
            "active" -> STATUS_ON
            "off" -> STATUS_OFF
            else -> STATUS_EXEC
        }
    }

    companion object {
        private const val DAY = "d."
        private const val HOUR = "h."
        private const val MINUTE = "m."
        private const val HOUR_RU = "час"
        private const val MONTH_RU = "месяц"
        private const val CURRENCY_RU = "₽"
        private const val UPTIME = "Uptime"
        private const val STATUS_NEW = "создание"
        private const val STATUS_ON = "работает"
        private const val STATUS_OFF = "выключен"
        private const val STATUS_EXEC = "выполнение"
    }
}