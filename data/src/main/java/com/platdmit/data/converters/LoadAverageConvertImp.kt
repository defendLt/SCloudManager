package com.platdmit.data.converters

import com.platdmit.data.retrofit.models.ApiLoadAverage
import com.platdmit.data.room.entity.DbLoadAverage
import com.platdmit.domain.converters.LoadAverageConverter
import com.platdmit.domain.models.LoadAverage
import javax.inject.Inject

class LoadAverageConvertImp
@Inject
constructor() : LoadAverageConverter<ApiLoadAverage, LoadAverage, DbLoadAverage> {
    override fun fromApiToDb(apiLoadAverage: ApiLoadAverage, serverId: Long): List<DbLoadAverage> {
        val loadAverageList: MutableList<DbLoadAverage> = emptyList<DbLoadAverage>().toMutableList();
        loadAverageList.add(DbLoadAverage(
                CPU_NAME,
                serverId,
                apiLoadAverage.cPU.total,
                "${apiLoadAverage.cPU.hourly} $VAL_PERCENT",
                apiLoadAverage.cPU.hourly
        ))
        loadAverageList.add(DbLoadAverage(
                RAM_NAME,
                serverId,
                apiLoadAverage.rAM.total,
                "${apiLoadAverage.rAM.hourly} $VAL_PERCENT",
                apiLoadAverage.rAM.hourly
        ))
        loadAverageList.add(DbLoadAverage(
                ROM_NAME,
                serverId,
                apiLoadAverage.disk.total,
                "${apiLoadAverage.disk.free} $VAL_SIZE",
                getFreePercent(apiLoadAverage.disk.total, apiLoadAverage.disk.free)
        ))
        return loadAverageList
    }

    override fun fromApiToDomain(apiLoadAverage: ApiLoadAverage): List<LoadAverage> {
        val loadAverageList: MutableList<LoadAverage> = emptyList<LoadAverage>().toMutableList();
        loadAverageList.add(LoadAverage(
                CPU_NAME,
                apiLoadAverage.cPU.total,
                "${apiLoadAverage.cPU.hourly} $VAL_PERCENT",
                apiLoadAverage.cPU.hourly
        ))
        loadAverageList.add(LoadAverage(
                RAM_NAME,
                apiLoadAverage.rAM.total,
                "${apiLoadAverage.rAM.hourly} $VAL_PERCENT",
                apiLoadAverage.rAM.hourly
        ))
        loadAverageList.add(LoadAverage(
                ROM_NAME,
                apiLoadAverage.disk.total,
                "${apiLoadAverage.disk.free} $VAL_SIZE",
                getFreePercent(apiLoadAverage.disk.total, apiLoadAverage.disk.free)
        ))
        return loadAverageList
    }

    override fun fromDbToDomain(dbLoadAverages: List<DbLoadAverage>): List<LoadAverage> {
        return dbLoadAverages.map {
            LoadAverage(
                    it.name,
                    it.total,
                    it.value,
                    it.percent
            )
        }
    }

    private fun getFreePercent(total: String, free: String): Double {
        val numTotal = total.toDouble()
        val numFree = free.toDouble()
        return numFree / numTotal * 100
    }

    companion object {
        private const val CPU_NAME = "Процессор"
        private const val RAM_NAME = "Память"
        private const val ROM_NAME = "Дисковое хранилище"
        private const val VAL_PERCENT = "%"
        private const val VAL_SIZE = "Gb"
    }
}