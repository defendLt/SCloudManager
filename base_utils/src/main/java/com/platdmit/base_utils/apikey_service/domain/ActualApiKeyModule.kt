package com.platdmit.base_utils.apikey_service.domain

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class ActualApiKeyModule(
//        private val mApiAccountRepo: ApiAccountRepo
) : ActualApiKeyServiceManager {
    private val DEMO_API_KEY = "kdDZDD9pNgv1jiakld784riyjiAtXzQj"
    private val mCompositeDisposable = CompositeDisposable()
    private lateinit var mValidAccount: BehaviorSubject<Boolean>
    private lateinit var mUserAccount: UserAccount
    override var apiKey: String = ""
    
    override fun setActiveAccount(userAccount: UserAccount) {
        stopAutoUpdate()
        startAutoUpdate(userAccount)
    }

    override fun startAutoUpdate(activeAccount: UserAccount) {
        mValidAccount = BehaviorSubject.create()
        mUserAccount = activeAccount
        startAutoUpdater()
    }

    override fun stopAutoUpdate() {
        mValidAccount.onComplete()
        mCompositeDisposable.clear()
    }

    override fun getAccountStatus(): BehaviorSubject<Boolean> {
        return mValidAccount
    }

    override fun startDemoMode() {
        apiKey = DEMO_API_KEY
    }

    private fun startAutoUpdater() {
//        mCompositeDisposable.add(
//                mApiAccountRepo.getApiKey(mUserAccount.login, mUserAccount.pass)
//                        .subscribeOn(Schedulers.newThread())
//                        .onErrorComplete {
//                            mValidAccount.onNext(false)
//                            true
//                        }
//                        .doOnSuccess {mValidAccount.onNext(true) }
//                        .repeatWhen { it.filter { mValidAccount.value }.delay(25, TimeUnit.MINUTES).onErrorComplete() }
//                        .subscribe { apiKey = it.sessionKey }
//        )
    }

}