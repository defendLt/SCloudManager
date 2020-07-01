package com.platdmit.simplecloudmanager.app.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import com.platdmit.simplecloudmanager.domain.models.LoadAverage
import com.platdmit.simplecloudmanager.domain.repo.ServerLoadAveragesRepo
import io.reactivex.rxjava3.processors.BehaviorProcessor

class LoadAverageViewModel(
        private val mLoadAveragesRepo: ServerLoadAveragesRepo,
        serverId: Long
) : BaseViewModel() {
    val loadAveragesLiveData: LiveData<List<LoadAverage>>
    val resultMassage = MutableLiveData<String>()
    private val mContentProvider = BehaviorProcessor.create<List<LoadAverage>>()

    init {
        //Fast fix for prevent overSubscription after resize
        mCompositeDisposable.add(mLoadAveragesRepo.getServerLoadAverages(serverId).subscribe {mContentProvider.onNext(it)})
        loadAveragesLiveData = LiveDataReactiveStreams.fromPublisher(mContentProvider)
    }

    override fun onCleared() {
        super.onCleared()
        mContentProvider.onComplete()
    }

    companion object {
        private val TAG = LoadAverageViewModel::class.java.simpleName
    }
}