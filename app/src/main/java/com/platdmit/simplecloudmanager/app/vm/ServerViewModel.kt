package com.platdmit.simplecloudmanager.app.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import com.platdmit.simplecloudmanager.domain.models.Server
import com.platdmit.simplecloudmanager.domain.repo.ServerBaseRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.processors.BehaviorProcessor
import io.reactivex.rxjava3.schedulers.Schedulers

class ServerViewModel(
        private val mServerRepo: ServerBaseRepo,
        id: Long
) : BaseViewModel() {
    val serverLiveData: LiveData<Server>
    val resultMassage = MediatorLiveData<String>()
    private val mContentProvider = BehaviorProcessor.create<Server>()

    init {
        //Fast fix for prevent overSubscription after resize
        mCompositeDisposable.add(
                mServerRepo.getServer(id)
                        .subscribeOn(Schedulers.newThread())
                        .onErrorComplete {
                            println(it.message)
                            true
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {mContentProvider.onNext(it)}
        )
        serverLiveData = LiveDataReactiveStreams.fromPublisher(mContentProvider)
    }

    override fun onCleared() {
        super.onCleared()
        mContentProvider.onComplete()
    }

    companion object {
        private val TAG = ServerViewModel::class.java.simpleName
    }
}