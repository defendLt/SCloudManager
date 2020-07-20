package com.platdmit.simplecloudmanager.states

import com.platdmit.domain.models.LoadAverage

sealed class LoadAverageState {
    data class Success(val loadAverages: List<LoadAverage>) : LoadAverageState()
    object Empty : LoadAverageState()
    object Loading : LoadAverageState()
    object Error : LoadAverageState()
}