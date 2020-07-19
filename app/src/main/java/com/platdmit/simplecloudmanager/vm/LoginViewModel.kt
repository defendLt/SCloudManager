package com.platdmit.simplecloudmanager.vm

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.platdmit.domain.helpers.ActualApiKeyServiceManager
import com.platdmit.domain.models.UserAccount
import com.platdmit.domain.repo.AccountRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.mindrot.BCrypt

class LoginViewModel
@ViewModelInject
constructor(
        private val accountRepo: AccountRepo,
        private val actualApiKeyServiceManager: ActualApiKeyServiceManager,
        @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    val authStatus = MutableLiveData<LoginFormStatus>()
    val regStatus = MutableLiveData<LoginFormStatus>()
    private lateinit var userAccount: UserAccount

    init {
        compositeDisposable.add(
                accountRepo.getActiveAccount()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            if (it.pin.isEmpty()) {
                                userAccount = it
                                authStatus.postValue(LoginFormStatus.YES_ACTIVE_USER)
                            } else {
                                authStatus.postValue(LoginFormStatus.NEED_SET_PIN)
                            }
                        }, {authStatus.postValue(LoginFormStatus.NOT_ACTIVE_USER) })
        )
    }

    fun addNewAccount(login: String, pass: String) {
        compositeDisposable.add(accountRepo.getPrepareAccountInfo(login, pass)
                .subscribe({
                    userAccount = it
                    regStatus.postValue(LoginFormStatus.NEED_SET_PIN)
                }, {
                    regStatus.postValue(LoginFormStatus.AUTH_INVALID)
                })
        )
    }

    fun addNewAccountPin(pin: String) {
        userAccount.pin = pin
        compositeDisposable.add(
                accountRepo.addAccountPin(userAccount)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread())
                        .subscribe {
                            actualApiKeyServiceManager.startAutoUpdate(userAccount)
                            authStatus.postValue(LoginFormStatus.SUCCESS)
                        }
        )
    }

    fun checkAccountPin(pin: String?) {
        Completable.fromAction {
            if (BCrypt.checkpw(pin, userAccount.pin)) {
                authStatus.postValue(LoginFormStatus.LOAD_DATA)
                actualApiKeyServiceManager.startAutoUpdate(userAccount)
                successAuth()
            } else {
                authStatus.postValue(LoginFormStatus.PIN_INVALID)
            }
        }.observeOn(Schedulers.newThread()).subscribe()
    }

    private fun successAuth() {
        compositeDisposable.add(
                actualApiKeyServiceManager.getAccountStatus()
                        .observeOn(Schedulers.newThread())
                        .onErrorComplete()
                        .subscribe {if (it) authStatus.postValue(LoginFormStatus.SUCCESS) }
        )
    }

    fun onDemoAccount() {
        actualApiKeyServiceManager.startDemoMode()
        authStatus.postValue(LoginFormStatus.ON_DEMO)
    }

    enum class LoginFormStatus {
        YES_ACTIVE_USER,
        NOT_ACTIVE_USER,
        PIN_INVALID,
        AUTH_INVALID,
        NEED_SET_PIN,
        LOAD_DATA,
        ON_DEMO,
        SUCCESS
    }
}