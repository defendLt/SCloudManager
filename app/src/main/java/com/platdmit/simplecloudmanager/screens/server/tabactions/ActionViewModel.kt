package com.platdmit.simplecloudmanager.screens.server.tabactions

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.SavedStateHandle
import com.platdmit.domain.repositories.ServerActionsRepo
import com.platdmit.simplecloudmanager.base.BaseViewModel
import com.platdmit.simplecloudmanager.base.extensions.toComposite

class ActionViewModel
@ViewModelInject
constructor(
        private val serverActionsRepo: ServerActionsRepo,
        @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel<ActionState>() {
    val actionStateLiveData = LiveDataReactiveStreams.fromPublisher(stateProvider)

    init {
        stateProvider.onNext(ActionState.Loading)
        savedStateHandle.get<Long>("ELEMENT_ID")?.let {
            setStateIntent(StateIntent.SetServerId(it))
        }
    }

    fun setStateIntent(stateIntent: StateIntent){
        when(stateIntent){
            is StateIntent.SetServerId -> {
                setActiveId(stateIntent.id)
            }
            is StateIntent.RefreshResult -> {}
        }
    }

    private fun setActiveId(id: Long) {
        serverActionsRepo.getServerActions(id)
                .subscribe({
                    stateProvider.onNext(ActionState.Success(it))
                }, {
                    stateProvider.onNext(ActionState.Error)
                }).toComposite(compositeDisposable)
    }

    sealed class StateIntent {
        data class SetServerId(val id: Long) : StateIntent()
        object RefreshResult : StateIntent()
    }
}