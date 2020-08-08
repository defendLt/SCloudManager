package com.platdmit.feature_servers.screens.server.tabaction

import com.platdmit.feature_servers.domain.models.Action

sealed class ActionState {
    data class Success(val actions: List<_root_ide_package_.com.platdmit.feature_servers.domain.models.Action>) : ActionState()
    object Empty : ActionState()
    object Loading : ActionState()
    object Error : ActionState()
}