package com.platdmit.mod_servers.screens.server

import com.platdmit.mod_servers.domain.models.Server

sealed class ServerState {
    data class Success(val server: Server) : ServerState()
    object Empty : ServerState()
    object Loading : ServerState()
    object Error : ServerState()
}