package com.platdmit.simplecloudmanager.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.platdmit.domain.helpers.ActualApiKeyServiceManager
import com.platdmit.domain.models.UserAccount
import com.platdmit.domain.repo.AccountRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.mindrot.BCrypt

class LoginViewModel(
        private val mAccountRep: AccountRepo,
        private val mActualApiKeyServiceManager: ActualApiKeyServiceManager
) : BaseViewModel() {
    private val mAuthStatus = MutableLiveData<LoginFormStatus>()
    private val mRegStatus = MutableLiveData<LoginFormStatus>()
    private lateinit var mUserAccount: UserAccount

    init {
        mCompositeDisposable.add(
                mAccountRep.getActiveAccount()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            if (it.pin.isEmpty()) {
                                mUserAccount = it
                                mAuthStatus.postValue(LoginFormStatus.YES_ACTIVE_USER)
                            } else {
                                mAuthStatus.postValue(LoginFormStatus.NEED_SET_PIN)
                            }
                        }, {mAuthStatus.postValue(LoginFormStatus.NOT_ACTIVE_USER) })
        )
    }

    fun addNewAccount(login: String, pass: String) {
        mCompositeDisposable.add(mAccountRep.getPrepareAccountInfo(login, pass)
                .subscribe({
                    mUserAccount = it
                    mRegStatus.postValue(LoginFormStatus.NEED_SET_PIN)
                }, {mRegStatus.postValue(LoginFormStatus.AUTH_INVALID) })
        )
    }

    fun addNewAccountPin(pin: String) {
        mUserAccount.pin = pin
        mCompositeDisposable.add(
                mAccountRep.addAccountPin(mUserAccount)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread())
                        .subscribe {
                            mActualApiKeyServiceManager.startAutoUpdate(mUserAccount)
                            mAuthStatus.postValue(LoginFormStatus.SUCCESS)
                        }
        )
    }

    fun checkAccountPin(pin: String?) {
        Completable.fromAction {
            if (BCrypt.checkpw(pin, mUserAccount.pin)) {
                mAuthStatus.postValue(LoginFormStatus.LOAD_DATA)
                mActualApiKeyServiceManager.startAutoUpdate(mUserAccount)
                successAuth()
            } else {
                mAuthStatus.postValue(LoginFormStatus.PIN_INVALID)
            }
        }.observeOn(Schedulers.newThread()).subscribe()
    }

    private fun successAuth() {
        mCompositeDisposable.add(
                mActualApiKeyServiceManager.getAccountStatus()
                        .observeOn(Schedulers.newThread())
                        .onErrorComplete()
                        .subscribe {if (it) mAuthStatus.postValue(LoginFormStatus.SUCCESS) }
        )
    }

    fun onDemoAccount() {
        mActualApiKeyServiceManager.startDemoMode()
        mAuthStatus.postValue(LoginFormStatus.ON_DEMO)
    }

    val authStatus: LiveData<LoginFormStatus>
        get() = mAuthStatus

    val regStatus: LiveData<LoginFormStatus>
        get() = mRegStatus

    enum class LoginFormStatus {
        YES_ACTIVE_USER, NOT_ACTIVE_USER, PIN_INVALID, AUTH_INVALID, NEED_SET_PIN, LOAD_DATA, ON_DEMO, SUCCESS
    }

    companion object {
        private val TAG = LoginViewModel::class.java.simpleName
    }
}