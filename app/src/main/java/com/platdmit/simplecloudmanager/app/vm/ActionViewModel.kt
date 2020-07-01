package com.platdmit.simplecloudmanager.app.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import com.platdmit.simplecloudmanager.domain.models.Action
import com.platdmit.simplecloudmanager.domain.repo.ServerActionsRepo
import io.reactivex.rxjava3.processors.BehaviorProcessor

class ActionViewModel(
        private val mActionsRepo: ServerActionsRepo,
        serverId: Long
) : BaseViewModel() {
    val actionsLiveData: LiveData<List<Action>>
    val resultMassage = MutableLiveData<String>()
    private val mContentProvider = BehaviorProcessor.create<List<Action>>()

    init {
        //Fast fix for prevent overSubscription after resize
        mCompositeDisposable.add(mActionsRepo.getServerActions(serverId).subscribe { mContentProvider.onNext(it) })
        actionsLiveData = LiveDataReactiveStreams.fromPublisher(mContentProvider)
    }

    override fun onCleared() {
        super.onCleared()
        mContentProvider.onComplete()
    }

    companion object {
        private val TAG = ActionViewModel::class.java.simpleName
    }
}