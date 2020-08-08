package com.platdmit.feature_servers.screens.server.tabaction

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.SavedStateHandle
import com.platdmit.feature_base.vm.BaseViewModel
import com.platdmit.feature_servers.domain.repositories.ServerActionsRepo

class ActionViewModel
@ViewModelInject
constructor(
        private val serverActionsRepo: ServerActionsRepo,
        @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel<ActionState>() {
    val actionStateLiveData = LiveDataReactiveStreams.fromPublisher(stateProvider)
    val messageLiveData = LiveDataReactiveStreams.fromPublisher(messageProvider)

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

    private fun setActiveId(id: Long){
        compositeDisposable.add(
                serverActionsRepo.getServerActions(id)
                        .subscribe({
                            stateProvider.onNext(ActionState.Success(it))
                        }, {
                            stateProvider.onNext(ActionState.Error)
                        })
        )
    }

    sealed class StateIntent {
        data class SetServerId(val id: Long) : StateIntent()
        object RefreshResult : StateIntent()
    }
}