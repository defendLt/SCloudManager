package com.platdmit.feature_servers.data.converters

import com.platdmit.feature_servers.domain.converters.LoadAverageConverter
import com.platdmit.feature_servers.domain.models.LoadAverage
import com.platdmit.feature_servers.data.retrofit.models.ApiLoadAverage
import com.platdmit.feature_servers.data.room.entity.DbLoadAverage
import javax.inject.Inject

class LoadAverageConvertImp
@Inject
constructor() : _root_ide_package_.com.platdmit.feature_servers.domain.converters.LoadAverageConverter<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiLoadAverage, _root_ide_package_.com.platdmit.feature_servers.domain.models.LoadAverage, _root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbLoadAverage> {
    override fun fromApiToDb(apiLoadAverage: _root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiLoadAverage, serverId: Long): List<_root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbLoadAverage> {
        val loadAverageList: MutableList<_root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbLoadAverage> = emptyList<_root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbLoadAverage>().toMutableList();
        loadAverageList.add(_root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbLoadAverage(
                _root_ide_package_.com.platdmit.feature_servers.data.converters.LoadAverageConvertImp.Companion.CPU_NAME,
                serverId,
                apiLoadAverage.cPU.getTotal(), apiLoadAverage.cPU.getHourly().toString() + _root_ide_package_.com.platdmit.feature_servers.data.converters.LoadAverageConvertImp.Companion.VAL_PERCENT,
                apiLoadAverage.cPU.getHourly()
        ))
        loadAverageList.add(_root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbLoadAverage(
                _root_ide_package_.com.platdmit.feature_servers.data.converters.LoadAverageConvertImp.Companion.RAM_NAME,
                serverId,
                apiLoadAverage.rAM.getTotal(), apiLoadAverage.rAM.getHourly().toString() + _root_ide_package_.com.platdmit.feature_servers.data.converters.LoadAverageConvertImp.Companion.VAL_PERCENT,
                apiLoadAverage.rAM.getHourly()
        ))
        loadAverageList.add(_root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbLoadAverage(
                _root_ide_package_.com.platdmit.feature_servers.data.converters.LoadAverageConvertImp.Companion.ROM_NAME,
                serverId,
                apiLoadAverage.disk.getTotal(),
                apiLoadAverage.disk.getFree() + _root_ide_package_.com.platdmit.feature_servers.data.converters.LoadAverageConvertImp.Companion.VAL_SIZE,
                getFreePercent(apiLoadAverage.disk.getTotal(), apiLoadAverage.disk.getFree())
        ))
        return loadAverageList
    }

    override fun fromApiToDomain(apiLoadAverage: _root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiLoadAverage): List<_root_ide_package_.com.platdmit.feature_servers.domain.models.LoadAverage> {
        val loadAverageList: MutableList<_root_ide_package_.com.platdmit.feature_servers.domain.models.LoadAverage> = emptyList<_root_ide_package_.com.platdmit.feature_servers.domain.models.LoadAverage>().toMutableList();
        loadAverageList.add(_root_ide_package_.com.platdmit.feature_servers.domain.models.LoadAverage(
                _root_ide_package_.com.platdmit.feature_servers.data.converters.LoadAverageConvertImp.Companion.CPU_NAME,
                apiLoadAverage.cPU.getTotal(), apiLoadAverage.cPU.getHourly().toString() + _root_ide_package_.com.platdmit.feature_servers.data.converters.LoadAverageConvertImp.Companion.VAL_PERCENT,
                apiLoadAverage.cPU.getHourly()
        ))
        loadAverageList.add(_root_ide_package_.com.platdmit.feature_servers.domain.models.LoadAverage(
                _root_ide_package_.com.platdmit.feature_servers.data.converters.LoadAverageConvertImp.Companion.RAM_NAME,
                apiLoadAverage.rAM.getTotal(), apiLoadAverage.rAM.getHourly().toString() + _root_ide_package_.com.platdmit.feature_servers.data.converters.LoadAverageConvertImp.Companion.VAL_PERCENT,
                apiLoadAverage.rAM.getHourly()
        ))
        loadAverageList.add(_root_ide_package_.com.platdmit.feature_servers.domain.models.LoadAverage(
                _root_ide_package_.com.platdmit.feature_servers.data.converters.LoadAverageConvertImp.Companion.ROM_NAME,
                apiLoadAverage.disk.getTotal(),
                apiLoadAverage.disk.getFree() + _root_ide_package_.com.platdmit.feature_servers.data.converters.LoadAverageConvertImp.Companion.VAL_SIZE,
                getFreePercent(apiLoadAverage.disk.getTotal(), apiLoadAverage.disk.getFree())
        ))
        return loadAverageList
    }

    override fun fromDbToDomain(dbLoadAverages: List<_root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbLoadAverage>): List<_root_ide_package_.com.platdmit.feature_servers.domain.models.LoadAverage> {
        return dbLoadAverages.map {
            _root_ide_package_.com.platdmit.feature_servers.domain.models.LoadAverage(
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