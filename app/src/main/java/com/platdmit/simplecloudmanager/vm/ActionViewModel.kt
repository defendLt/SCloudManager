package com.platdmit.simplecloudmanager.vm

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.SavedStateHandle
import com.platdmit.domain.models.Action
import com.platdmit.domain.repo.ServerActionsRepo
import com.platdmit.simplecloudmanager.states.ActionState
import io.reactivex.rxjava3.processors.BehaviorProcessor

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
    }

    fun setStateInstance(stateInstance: StateInstance){
        when(stateInstance){
            is StateInstance.SetServerId -> {
                setActiveId(stateInstance.id)
            }
            is StateInstance.RefreshResult -> {}
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

    sealed class StateInstance {
        data class SetServerId(val id: Long) : StateInstance()
        object RefreshResult : StateInstance()
    }
}