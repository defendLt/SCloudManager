package com.platdmit.simplecloudmanager.vm

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.SavedStateHandle
import com.platdmit.domain.models.Server
import com.platdmit.domain.repo.ServerBaseRepo
import io.reactivex.rxjava3.processors.BehaviorProcessor

class ServerListViewModel
@ViewModelInject
constructor(
        private val mServerRepo: ServerBaseRepo,
        @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    val serversLiveData: LiveData<List<Server>>
    val messageLiveData = LiveDataReactiveStreams.fromPublisher(messageProvider)
    private val contentProvider = BehaviorProcessor.create<List<Server>>()

    init {
        //Fast fix for prevent overSubscription after resize
        compositeDisposable.add(mServerRepo.getServers().subscribe { contentProvider.onNext(it) })
        serversLiveData = LiveDataReactiveStreams.fromPublisher(contentProvider)
    }

    fun reloadServerList() {
        mServerRepo.nextUpdate()
    }

    override fun onCleared() {
        super.onCleared()
        contentProvider.onComplete()
    }
}