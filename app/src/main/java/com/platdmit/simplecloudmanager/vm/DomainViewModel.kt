package com.platdmit.simplecloudmanager.vm

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.SavedStateHandle
import com.platdmit.domain.models.Domain
import com.platdmit.domain.repo.DomainBaseRepo
import io.reactivex.rxjava3.processors.BehaviorProcessor

class DomainViewModel
@ViewModelInject
constructor(
        private val domainBaseRepo: DomainBaseRepo,
        @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    val domainLiveData: LiveData<Domain>
    val messageLiveData = LiveDataReactiveStreams.fromPublisher(messageProvider)

    private val contentProvider = BehaviorProcessor.create<Domain>()

    init {
        domainLiveData = LiveDataReactiveStreams.fromPublisher(contentProvider)
    }

    fun setActiveId(id: Long){
        //Fast fix for prevent overSubscription after resize
        compositeDisposable.add(
                domainBaseRepo.getDomain(id).subscribe { contentProvider.onNext(it) }
        )
    }

    override fun onCleared() {
        super.onCleared()
        contentProvider.onComplete()
    }
}