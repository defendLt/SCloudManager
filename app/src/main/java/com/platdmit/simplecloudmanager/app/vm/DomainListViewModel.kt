package com.platdmit.simplecloudmanager.app.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import com.platdmit.simplecloudmanager.domain.models.Domain
import com.platdmit.simplecloudmanager.domain.repo.DomainBaseRepo
import io.reactivex.rxjava3.processors.BehaviorProcessor

class DomainListViewModel(
        private val mDomainRepo: DomainBaseRepo
) : BaseViewModel() {
    val domainsLiveData: LiveData<List<Domain>>
    val resultMassage = MutableLiveData<String>()
    private val mContentProvider = BehaviorProcessor.create<List<Domain>>()

    init {
        //Fast fix for prevent overSubscription after resize
        mCompositeDisposable.add(mDomainRepo.getDomains().subscribe {mContentProvider.onNext(it)})
        domainsLiveData = LiveDataReactiveStreams.fromPublisher(mContentProvider)
    }

    fun reloadDomainList() {
        mDomainRepo.nextUpdate()
    }

    override fun onCleared() {
        super.onCleared()
        mContentProvider.onComplete()
    }

    companion object {
        private val TAG = DomainListViewModel::class.java.simpleName
    }
}