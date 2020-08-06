package com.platdmit.mod_servers.screens.servers

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.SavedStateHandle
import com.platdmit.mod_servers.domain.repositories.ServerBaseRepo
import com.platdmit.simplecloudmanager.vm.BaseViewModel

class ServerListViewModel
@ViewModelInject
constructor(
        private val mServerRepo: ServerBaseRepo,
        @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel<ServerListState>() {
    val serversStateLiveData = LiveDataReactiveStreams.fromPublisher(stateProvider)
    val messageLiveData = LiveDataReactiveStreams.fromPublisher(messageProvider)

    init {
        stateProvider.onNext(ServerListState.Loading)
        //Fast fix for prevent overSubscription after resize
        compositeDisposable.add(mServerRepo.getServers()
                .subscribe({
                    stateProvider.onNext(ServerListState.Success(it))
                }, {
                    stateProvider.onNext(ServerListState.Error)
                })
        )
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