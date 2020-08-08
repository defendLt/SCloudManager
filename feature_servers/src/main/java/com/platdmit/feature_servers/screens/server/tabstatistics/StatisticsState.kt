package com.platdmit.feature_servers.screens.server.tabstatistics

import com.platdmit.feature_servers.domain.models.ComplexChartsData

sealed class StatisticsState {
    data class Success(
            val cpuData: _root_ide_package_.com.platdmit.feature_servers.domain.models.ComplexChartsData,
            val ramData: _root_ide_package_.com.platdmit.feature_servers.domain.models.ComplexChartsData
    ) : StatisticsState()
    object Empty : StatisticsState()
    object Loading : StatisticsState()
    object Error : StatisticsState()
}