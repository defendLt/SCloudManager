package com.platdmit.simplecloudmanager.vm

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.SavedStateHandle
import com.platdmit.domain.models.Server
import com.platdmit.domain.repo.ServerBaseRepo
import com.platdmit.simplecloudmanager.states.ServerState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.processors.BehaviorProcessor
import io.reactivex.rxjava3.schedulers.Schedulers

class ServerViewModel
@ViewModelInject
constructor(
        private val serverBaseRepo: ServerBaseRepo,
        @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel<ServerState>() {
    val serverStateLiveData = LiveDataReactiveStreams.fromPublisher(stateProvider)
    val messageLiveData = LiveDataReactiveStreams.fromPublisher(messageProvider)

    init {
        stateProvider.onNext(ServerState.Loading)
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
        compositeDisposable.add(
                serverBaseRepo.getServer(id)
                        .subscribeOn(Schedulers.newThread())
                        .onErrorComplete {
                            println(it.message)
                            true
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe ({
                            stateProvider.onNext(ServerState.Success(it))
                        },{
                            stateProvider.onNext(ServerState.Error)
                        })
        )
    }

    sealed class StateIntent {
        data class SetServerId(val id: Long) : StateIntent()
        object RefreshResult : StateIntent()
    }
}