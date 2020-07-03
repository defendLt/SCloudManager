package com.platdmit.domain.converters.implement

import com.platdmit.data.api.models.ApiLoadAverage
import com.platdmit.data.database.entity.DbLoadAverage
import com.platdmit.domain.converters.LoadAverageConverter
import com.platdmit.domain.models.LoadAverage
import java.util.*

class LoadAverageConvertImp : LoadAverageConverter {
    override fun fromApiToDb(apiLoadAverage: ApiLoadAverage, serverId: Long): List<DbLoadAverage> {
        val loadAverageList: MutableList<DbLoadAverage> = emptyList<DbLoadAverage>().toMutableList();
        loadAverageList.add(DbLoadAverage(
                CPU_NAME,
                serverId,
                apiLoadAverage.cPU.getTotal(), apiLoadAverage.cPU.getHourly().toString() + VAL_PERCENT,
                apiLoadAverage.cPU.getHourly()
        ))
        loadAverageList.add(DbLoadAverage(
                RAM_NAME,
                serverId,
                apiLoadAverage.rAM.getTotal(), apiLoadAverage.rAM.getHourly().toString() + VAL_PERCENT,
                apiLoadAverage.rAM.getHourly()
        ))
        loadAverageList.add(DbLoadAverage(
                ROM_NAME,
                serverId,
                apiLoadAverage.disk.getTotal(),
                apiLoadAverage.disk.getFree() + VAL_SIZE,
                getFreePercent(apiLoadAverage.disk.getTotal(), apiLoadAverage.disk.getFree())
        ))
        return loadAverageList
    }

    override fun fromApiToDomain(apiLoadAverage: ApiLoadAverage): List<LoadAverage> {
        val loadAverageList: MutableList<LoadAverage> = emptyList<LoadAverage>().toMutableList();
        loadAverageList.add(LoadAverage(
                CPU_NAME,
                apiLoadAverage.cPU.getTotal(), apiLoadAverage.cPU.getHourly().toString() + VAL_PERCENT,
                apiLoadAverage.cPU.getHourly()
        ))
        loadAverageList.add(LoadAverage(
                RAM_NAME,
                apiLoadAverage.rAM.getTotal(), apiLoadAverage.rAM.getHourly().toString() + VAL_PERCENT,
                apiLoadAverage.rAM.getHourly()
        ))
        loadAverageList.add(LoadAverage(
                ROM_NAME,
                apiLoadAverage.disk.getTotal(),
                apiLoadAverage.disk.getFree() + VAL_SIZE,
                getFreePercent(apiLoadAverage.disk.getTotal(), apiLoadAverage.disk.getFree())
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