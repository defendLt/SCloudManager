package com.platdmit.mod_servers.screens.server.tabloadaverage

import com.platdmit.mod_servers.domain.models.LoadAverage

sealed class LoadAverageState {
    data class Success(val loadAverages: List<LoadAverage>) : LoadAverageState()
    object Empty : LoadAverageState()
    object Loading : LoadAverageState()
    object Error : LoadAverageState()
}