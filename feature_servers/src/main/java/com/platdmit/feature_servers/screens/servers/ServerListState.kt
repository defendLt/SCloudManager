package com.platdmit.feature_servers.screens.servers

import com.platdmit.feature_servers.domain.models.Server

sealed class ServerListState {
    data class Success(val servers: List<_root_ide_package_.com.platdmit.feature_servers.domain.models.Server>) : ServerListState()
    object Empty : ServerListState()
    object Loading : ServerListState()
    object Error : ServerListState()
}