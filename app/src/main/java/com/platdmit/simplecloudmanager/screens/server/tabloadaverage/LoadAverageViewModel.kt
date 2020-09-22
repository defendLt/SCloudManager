package com.platdmit.simplecloudmanager.screens.server.tabloadaverage

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.SavedStateHandle
import com.platdmit.domain.repositories.ServerLoadAveragesRepo
import com.platdmit.simplecloudmanager.base.BaseViewModel
import com.platdmit.simplecloudmanager.base.extensions.toComposite

class LoadAverageViewModel
@ViewModelInject
constructor(
        private val serverLoadAveragesRepo: ServerLoadAveragesRepo,
        @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel<LoadAverageState>() {
    val loadAverageStateLiveData = LiveDataReactiveStreams.fromPublisher(stateProvider)

    init {
        stateProvider.onNext(LoadAverageState.Loading)
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
        //Fast fix for prevent overSubscription after resize
        serverLoadAveragesRepo.getServerLoadAverages(id)
                .subscribe ({
                    stateProvider.onNext(LoadAverageState.Success(it))
                },{
                    stateProvider.onNext(LoadAverageState.Error)
                }).toComposite(compositeDisposable)
    }

    sealed class StateIntent {
        data class SetServerId(val id: Long) : StateIntent()
        object RefreshResult : StateIntent()
    }
}