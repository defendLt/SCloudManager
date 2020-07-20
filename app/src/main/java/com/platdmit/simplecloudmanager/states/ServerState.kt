package com.platdmit.simplecloudmanager.states

import com.platdmit.domain.models.Server

sealed class ServerState {
    data class Success(val server: Server) : ServerState()
    object Empty : ServerState()
    object Loading : ServerState()
    object Error : ServerState()
}