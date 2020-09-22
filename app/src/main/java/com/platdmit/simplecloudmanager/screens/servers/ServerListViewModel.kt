package com.platdmit.simplecloudmanager.screens.servers

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.SavedStateHandle
import com.platdmit.domain.repositories.ServerBaseRepo
import com.platdmit.simplecloudmanager.base.BaseViewModel
import com.platdmit.simplecloudmanager.base.extensions.toComposite

class ServerListViewModel
@ViewModelInject
constructor(
        private val mServerRepo: ServerBaseRepo,
        @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel<ServerListState>() {
    val serversStateLiveData = LiveDataReactiveStreams.fromPublisher(stateProvider)

    init {
        stateProvider.onNext(ServerListState.Loading)
        //Fast fix for prevent overSubscription after resize
        mServerRepo.getServers()
                .subscribe({
                    stateProvider.onNext(ServerListState.Success(it))
                }, {
                    stateProvider.onNext(ServerListState.Error)
                }).toComposite(compositeDisposable)
    }

    fun setStateIntent(stateIntent: StateIntent){
        when(stateIntent){
            is StateIntent.RefreshResult -> {
                reloadServerList()
            }
        }
    }

    private fun reloadServerList() {
        mServerRepo.nextUpdate()
    }

    sealed class StateIntent {
        object RefreshResult : StateIntent()
    }
}