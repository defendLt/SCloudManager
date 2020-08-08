package com.platdmit.feature_servers.screens.server.tabloadaverage

import com.platdmit.feature_servers.domain.models.LoadAverage

sealed class LoadAverageState {
    data class Success(val loadAverages: List<_root_ide_package_.com.platdmit.feature_servers.domain.models.LoadAverage>) : LoadAverageState()
    object Empty : LoadAverageState()
    object Loading : LoadAverageState()
    object Error : LoadAverageState()
}