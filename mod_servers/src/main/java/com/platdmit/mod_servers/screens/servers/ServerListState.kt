package com.platdmit.mod_servers.screens.servers

import com.platdmit.mod_servers.domain.models.Server

sealed class ServerListState {
    data class Success(val servers: List<Server>) : ServerListState()
    object Empty : ServerListState()
    object Loading : ServerListState()
    object Error : ServerListState()
}