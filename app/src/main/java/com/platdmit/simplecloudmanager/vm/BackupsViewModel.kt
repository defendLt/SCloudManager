package com.platdmit.simplecloudmanager.vm

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.SavedStateHandle
import com.platdmit.domain.models.Backup
import com.platdmit.domain.repo.ServerBackupRepo
import com.platdmit.simplecloudmanager.states.BackupsState
import io.reactivex.rxjava3.processors.BehaviorProcessor

class BackupsViewModel
@ViewModelInject
constructor(
        private val serverBackupRepo: ServerBackupRepo,
        @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel<BackupsState>() {
    val backupsLiveData: LiveData<List<Backup>>
    val messageLiveData = LiveDataReactiveStreams.fromPublisher(messageProvider)

    private val contentProvider = BehaviorProcessor.create<List<Backup>>()

    init {
        backupsLiveData = LiveDataReactiveStreams.fromPublisher(contentProvider)
    }

    fun setActiveId(id: Long){
        //Fast fix for prevent overSubscription after resize
        compositeDisposable.add(
                serverBackupRepo.getServerBackups(id)
                        .subscribe { contentProvider.onNext(it) }
        )
    }

    override fun onCleared() {
        super.onCleared()
        contentProvider.onComplete()
    }
}