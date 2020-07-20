package com.platdmit.simplecloudmanager.states

import com.platdmit.domain.models.ComplexChartsData

sealed class StatisticsState {
    data class Success(
            val cpuData: ComplexChartsData,
            val ramData: ComplexChartsData
    ) : StatisticsState()
    object Empty : StatisticsState()
    object Loading : StatisticsState()
    object Error : StatisticsState()
}