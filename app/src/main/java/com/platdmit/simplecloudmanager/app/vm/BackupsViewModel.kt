package com.platdmit.simplecloudmanager.app.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import com.platdmit.simplecloudmanager.domain.models.Backup
import com.platdmit.simplecloudmanager.domain.repo.ServerBackupRepo
import io.reactivex.rxjava3.processors.BehaviorProcessor

class BackupsViewModel(private val mBackupRepo: ServerBackupRepo, serverId: Long) : BaseViewModel() {
    val backupsLiveData: LiveData<List<Backup>>
    val resultMassage = MutableLiveData<String>()
    private val mContentProvider = BehaviorProcessor.create<List<Backup>>()

    init {
        //Fast fix for prevent overSubscription after resize
        mCompositeDisposable.add(mBackupRepo.getServerBackups(serverId).subscribe { mContentProvider.onNext(it) })
        backupsLiveData = LiveDataReactiveStreams.fromPublisher(mContentProvider)
    }

    companion object {
        private val TAG = BackupsViewModel::class.java.simpleName
    }

    override fun onCleared() {
        super.onCleared()
        mContentProvider.onComplete()
    }
}