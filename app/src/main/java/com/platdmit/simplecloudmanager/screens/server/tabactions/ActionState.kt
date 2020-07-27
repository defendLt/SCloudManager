package com.platdmit.simplecloudmanager.screens.server.tabactions

import com.platdmit.domain.models.Action

sealed class ActionState {
    data class Success(val actions: List<Action>) : ActionState()
    object Empty : ActionState()
    object Loading : ActionState()
    object Error : ActionState()
}