package com.platdmit.simplecloudmanager.vm

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.SavedStateHandle
import com.platdmit.domain.models.Domain
import com.platdmit.domain.repo.DomainBaseRepo
import com.platdmit.simplecloudmanager.states.DomainListState
import io.reactivex.rxjava3.processors.BehaviorProcessor

class DomainListViewModel
@ViewModelInject
constructor(
        private val domainBaseRepo: DomainBaseRepo,
        @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel<DomainListState>() {
    val domainsLiveData: LiveData<List<Domain>>
    val messageLiveData = LiveDataReactiveStreams.fromPublisher(messageProvider)

    private val contentProvider = BehaviorProcessor.create<List<Domain>>()

    init {
        //Fast fix for prevent overSubscription after resize
        compositeDisposable.add(domainBaseRepo.getDomains().subscribe {contentProvider.onNext(it)})
        domainsLiveData = LiveDataReactiveStreams.fromPublisher(contentProvider)
    }

    fun reloadDomainList() {
        domainBaseRepo.nextUpdate()
    }

    override fun onCleared() {
        super.onCleared()
        contentProvider.onComplete()
    }
}