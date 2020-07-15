package com.platdmit.simplecloudmanager.vm

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.SavedStateHandle
import com.platdmit.domain.models.Server
import com.platdmit.domain.repo.ServerBaseRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.processors.BehaviorProcessor
import io.reactivex.rxjava3.schedulers.Schedulers

class ServerViewModel
@ViewModelInject
constructor(
        private val serverBaseRepo: ServerBaseRepo,
        @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    val serverLiveData: LiveData<Server>
    val messageLiveData = LiveDataReactiveStreams.fromPublisher(messageProvider)

    private val contentProvider = BehaviorProcessor.create<Server>()

    init {
        serverLiveData = LiveDataReactiveStreams.fromPublisher(contentProvider)
    }

    fun setActiveId(id: Long){
        //Fast fix for prevent overSubscription after resize
        compositeDisposable.add(
                serverBaseRepo.getServer(id)
                        .subscribeOn(Schedulers.newThread())
                        .onErrorComplete {
                            println(it.message)
                            true
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {contentProvider.onNext(it)}
        )
    }

    override fun onCleared() {
        super.onCleared()
        contentProvider.onComplete()
    }
}