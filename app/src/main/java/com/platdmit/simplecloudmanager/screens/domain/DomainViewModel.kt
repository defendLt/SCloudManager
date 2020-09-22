package com.platdmit.simplecloudmanager.screens.domain

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.SavedStateHandle
import com.platdmit.domain.repositories.DomainBaseRepo
import com.platdmit.simplecloudmanager.base.BaseViewModel
import com.platdmit.simplecloudmanager.base.extensions.toComposite

class DomainViewModel
@ViewModelInject
constructor(
        private val domainBaseRepo: DomainBaseRepo,
        @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel<DomainState>() {
    val domainStateLiveData = LiveDataReactiveStreams.fromPublisher(stateProvider)

    init {
        stateProvider.onNext(DomainState.Loading)
        savedStateHandle.get<Long>("ELEMENT_ID")?.let {
            setStateIntent(StateIntent.SetDomainId(it))
        }
    }

    fun setStateIntent(stateIntent: StateIntent){
        when(stateIntent){
            is StateIntent.SetDomainId -> {
                setActiveId(stateIntent.id)
            }
            is StateIntent.RefreshResult -> {}
        }
    }

    private fun setActiveId(id: Long){
        //Fast fix for prevent overSubscription after resize
        domainBaseRepo.getDomain(id).subscribe ({
            stateProvider.onNext(DomainState.Success(it))
        },{
            stateProvider.onNext(DomainState.Error)
        }).toComposite(compositeDisposable)
    }

    sealed class StateIntent {
        data class SetDomainId(val id: Long) : StateIntent()
        object RefreshResult : StateIntent()
    }
}