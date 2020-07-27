package com.platdmit.simplecloudmanager.screens.servers

import com.platdmit.domain.models.Server

sealed class ServerListState {
    data class Success(val servers: List<Server>) : ServerListState()
    object Empty : ServerListState()
    object Loading : ServerListState()
    object Error : ServerListState()
}