package com.platdmit.simplecloudmanager.screens.domains

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.SavedStateHandle
import com.platdmit.domain.repositories.DomainBaseRepo
import com.platdmit.simplecloudmanager.base.BaseViewModel
import com.platdmit.simplecloudmanager.base.extensions.toComposite

class DomainListViewModel
@ViewModelInject
constructor(
        private val domainBaseRepo: DomainBaseRepo,
        @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel<DomainListState>() {
    val domainsStateLiveData = LiveDataReactiveStreams.fromPublisher(stateProvider)

    init {
        stateProvider.onNext(DomainListState.Loading)
        //Fast fix for prevent overSubscription after resize
        domainBaseRepo.getDomains()
                .subscribe({
                    stateProvider.onNext(DomainListState.Success(it))
                }, {
                    stateProvider.onNext(DomainListState.Error)
                }).toComposite(compositeDisposable)
    }

    fun setStateIntent(stateIntent: StateIntent){
        when(stateIntent){
            is StateIntent.RefreshResult -> {
                reloadDomainList()
            }
        }
    }

    private fun reloadDomainList() {
        domainBaseRepo.nextUpdate()
    }

    sealed class StateIntent {
        object RefreshResult : StateIntent()
    }
}