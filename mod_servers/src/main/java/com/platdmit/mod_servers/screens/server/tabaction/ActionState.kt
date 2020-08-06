package com.platdmit.mod_servers.screens.server.tabaction

import com.platdmit.mod_servers.domain.models.Action

sealed class ActionState {
    data class Success(val actions: List<Action>) : ActionState()
    object Empty : ActionState()
    object Loading : ActionState()
    object Error : ActionState()
}