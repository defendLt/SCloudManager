package com.platdmit.simplecloudmanager.app.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import com.platdmit.simplecloudmanager.domain.models.Server
import com.platdmit.simplecloudmanager.domain.repo.ServerBaseRepo
import io.reactivex.rxjava3.processors.BehaviorProcessor

class ServerListViewModel(
        private val mServerRepo: ServerBaseRepo
) : BaseViewModel() {
    val serversLiveData: LiveData<List<Server>>
    val resultMassage = MutableLiveData<String>()
    private val mContentProvider = BehaviorProcessor.create<List<Server>>()

    fun reloadServerList() {
        mServerRepo.nextUpdate()
    }

    init {
        //Fast fix for prevent overSubscription after resize
        mCompositeDisposable.add(mServerRepo.getServers().subscribe { mContentProvider.onNext(it) })
        serversLiveData = LiveDataReactiveStreams.fromPublisher(mContentProvider)
    }

    override fun onCleared() {
        super.onCleared()
        mContentProvider.onComplete()
    }

    companion object {
        private val TAG = ServerListViewModel::class.java.simpleName
    }
}