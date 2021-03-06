package com.platdmit.simplecloudmanager.screens.server.tabbackups

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.SavedStateHandle
import com.platdmit.domain.repositories.ServerBackupRepo
import com.platdmit.simplecloudmanager.base.BaseViewModel
import com.platdmit.simplecloudmanager.base.extensions.toComposite

class BackupsViewModel
@ViewModelInject
constructor(
        private val serverBackupRepo: ServerBackupRepo,
        @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel<BackupsState>() {
    val backupsStateLiveData = LiveDataReactiveStreams.fromPublisher(stateProvider)

    init {
        stateProvider.onNext(BackupsState.Loading)
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
        serverBackupRepo.getServerBackups(id)
                .subscribe({
                    stateProvider.onNext(BackupsState.Success(it))
                }, {
                    stateProvider.onNext(BackupsState.Error)
                }).toComposite(compositeDisposable)
    }

    sealed class StateIntent {
        data class SetServerId(val id: Long) : StateIntent()
        object RefreshResult : StateIntent()
    }
}