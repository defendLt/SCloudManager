package com.platdmit.simplecloudmanager.vm

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.SavedStateHandle
import com.platdmit.domain.models.Action
import com.platdmit.domain.repo.ServerActionsRepo
import io.reactivex.rxjava3.processors.BehaviorProcessor

class ActionViewModel
@ViewModelInject
constructor(
        private val serverActionsRepo: ServerActionsRepo,
        @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    val actionsLiveData: LiveData<List<Action>>
    val messageLiveData = LiveDataReactiveStreams.fromPublisher(messageProvider)

    private val mContentProvider = BehaviorProcessor.create<List<Action>>()

    init {
        //Fast fix for prevent overSubscription after resize
        actionsLiveData = LiveDataReactiveStreams.fromPublisher(mContentProvider)
    }

    fun setActiveId(id: Long){
        compositeDisposable.add(
                serverActionsRepo.getServerActions(id)
                        .subscribe { mContentProvider.onNext(it) }
        )
    }

    override fun onCleared() {
        super.onCleared()
        mContentProvider.onComplete()
    }
}