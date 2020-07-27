package com.platdmit.simplecloudmanager.screens.server

import com.platdmit.domain.models.Server

sealed class ServerState {
    data class Success(val server: Server) : ServerState()
    object Empty : ServerState()
    object Loading : ServerState()
    object Error : ServerState()
}