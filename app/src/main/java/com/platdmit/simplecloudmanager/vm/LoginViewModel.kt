package com.platdmit.simplecloudmanager.vm

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.platdmit.domain.helpers.ActualApiKeyServiceManager
import com.platdmit.domain.models.UserAccount
import com.platdmit.domain.repo.AccountRepo
import com.platdmit.simplecloudmanager.states.LoginState
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
) : BaseViewModel<LoginState>() {

    val loginStateLiveData = LiveDataReactiveStreams.fromPublisher(stateProvider)
    private lateinit var userAccount: UserAccount

    init {
        stateProvider.onNext(LoginState.Loading)
        compositeDisposable.add(
                accountRepo.getActiveAccount()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            if (it.pin.isEmpty()) {
                                userAccount = it
                                stateProvider.onNext(LoginState.ActiveUserYes)
                            } else {
                                stateProvider.onNext(LoginState.UserNeedPin)
                            }
                        }, {
                            stateProvider.onNext(LoginState.ActiveUserNo)
                        })
        )
    }

    fun setStateInstance(stateInstance: StateInstance){
        when(stateInstance){
            is StateInstance.NewAccount -> {
                addNewAccount(stateInstance.login, stateInstance.pass)
            }
            is StateInstance.NewAccountPin -> {
                addNewAccountPin(stateInstance.pin)
            }
            is StateInstance.CheckAccountPin -> {
                checkAccountPin(stateInstance.pin)
            }
            is StateInstance.OnDemoMode -> {
                onDemoAccount()
            }
        }
    }

    private fun addNewAccount(login: String, pass: String) {
        compositeDisposable.add(accountRepo.getPrepareAccountInfo(login, pass)
                .subscribe({
                    userAccount = it
                    stateProvider.onNext(LoginState.UserNeedPin)
                }, {
                    stateProvider.onNext(LoginState.AuthInvalid)
                })
        )
    }

    private fun addNewAccountPin(pin: String) {
        userAccount.pin = pin
        compositeDisposable.add(
                accountRepo.addAccountPin(userAccount)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread())
                        .subscribe {
                            actualApiKeyServiceManager.startAutoUpdate(userAccount)
                            stateProvider.onNext(LoginState.Success)
                        }
        )
    }

    private fun checkAccountPin(pin: String?) {
        Completable.fromAction {
            if (BCrypt.checkpw(pin, userAccount.pin)) {
                stateProvider.onNext(LoginState.Loading)
                actualApiKeyServiceManager.startAutoUpdate(userAccount)
                successAuth()
            } else {
                stateProvider.onNext(LoginState.PinInvalid)
            }
        }.observeOn(Schedulers.newThread()).subscribe()
    }

    private fun successAuth() {
        compositeDisposable.add(
                actualApiKeyServiceManager.getAccountStatus()
                        .observeOn(Schedulers.newThread())
                        .onErrorComplete()
                        .subscribe {
                            if (it) stateProvider.onNext(LoginState.Success)
                        }
        )
    }

    private fun onDemoAccount() {
        actualApiKeyServiceManager.startDemoMode()
        stateProvider.onNext(LoginState.OnDemo)
    }

    sealed class StateInstance {
        data class NewAccount(val login: String, val pass: String) : StateInstance()
        data class NewAccountPin(val pin: String) : StateInstance()
        data class CheckAccountPin(val pin: String?) : StateInstance()
        object OnDemoMode : StateInstance()
    }
}