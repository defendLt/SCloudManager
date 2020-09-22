package com.platdmit.simplecloudmanager.screens.server

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.SavedStateHandle
import com.platdmit.domain.repositories.ServerBaseRepo
import com.platdmit.simplecloudmanager.base.BaseViewModel
import com.platdmit.simplecloudmanager.base.extensions.toComposite
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ServerViewModel
@ViewModelInject
constructor(
        private val serverBaseRepo: ServerBaseRepo,
        @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel<ServerState>() {
    val serverStateLiveData = LiveDataReactiveStreams.fromPublisher(stateProvider)

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
        serverBaseRepo.getServer(id)
                .subscribeOn(Schedulers.newThread())
                .onErrorComplete {
                    println(it.message)
                    true
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    stateProvider.onNext(ServerState.Success(it))
                }, {
                    stateProvider.onNext(ServerState.Error)
                }).toComposite(compositeDisposable)
    }

    sealed class StateIntent {
        data class SetServerId(val id: Long) : StateIntent()
        object RefreshResult : StateIntent()
    }
}