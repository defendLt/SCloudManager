package com.platdmit.mod_servers.screens.server.tabstatistics

import com.platdmit.mod_servers.domain.models.ComplexChartsData

sealed class StatisticsState {
    data class Success(
            val cpuData: ComplexChartsData,
            val ramData: ComplexChartsData
    ) : StatisticsState()
    object Empty : StatisticsState()
    object Loading : StatisticsState()
    object Error : StatisticsState()
}