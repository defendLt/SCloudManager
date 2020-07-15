package com.platdmit.simplecloudmanager.vm

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.SavedStateHandle
import com.platdmit.domain.models.LoadAverage
import com.platdmit.domain.repo.ServerLoadAveragesRepo
import io.reactivex.rxjava3.processors.BehaviorProcessor

class LoadAverageViewModel
@ViewModelInject
constructor(
        private val serverLoadAveragesRepo: ServerLoadAveragesRepo,
        @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    val loadAveragesLiveData: LiveData<List<LoadAverage>>
    val messageLiveData = LiveDataReactiveStreams.fromPublisher(messageProvider)

    private val contentProvider = BehaviorProcessor.create<List<LoadAverage>>()

    init {
        loadAveragesLiveData = LiveDataReactiveStreams.fromPublisher(contentProvider)
    }

    fun setActiveId(id: Long){
        //Fast fix for prevent overSubscription after resize
        compositeDisposable.add(
                serverLoadAveragesRepo.getServerLoadAverages(id)
                        .subscribe {contentProvider.onNext(it)}
        )
    }

    override fun onCleared() {
        super.onCleared()
        contentProvider.onComplete()
    }
}