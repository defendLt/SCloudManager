package com.platdmit.feature_servers.screens.server

import com.platdmit.feature_servers.domain.models.Server

sealed class ServerState {
    data class Success(val server: _root_ide_package_.com.platdmit.feature_servers.domain.models.Server) : _root_ide_package_.com.platdmit.feature_servers.screens.server.ServerState()
    object Empty : _root_ide_package_.com.platdmit.feature_servers.screens.server.ServerState()
    object Loading : _root_ide_package_.com.platdmit.feature_servers.screens.server.ServerState()
    object Error : _root_ide_package_.com.platdmit.feature_servers.screens.server.ServerState()
}