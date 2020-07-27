package com.platdmit.simplecloudmanager.screens.login

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.SavedStateHandle
import com.platdmit.domain.helpers.ActualApiKeyServiceManager
import com.platdmit.domain.models.UserAccount
import com.platdmit.domain.repositories.AccountRepo
import com.platdmit.simplecloudmanager.vm.BaseViewModel
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

    fun setStateIntent(stateIntent: StateIntent){
        when(stateIntent){
            is StateIntent.NewAccount -> {
                addNewAccount(stateIntent.login, stateIntent.pass)
            }
            is StateIntent.NewAccountPin -> {
                addNewAccountPin(stateIntent.pin)
            }
            is StateIntent.CheckAccountPin -> {
                checkAccountPin(stateIntent.pin)
            }
            is StateIntent.OnDemoMode -> {
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

    sealed class StateIntent {
        data class NewAccount(val login: String, val pass: String) : StateIntent()
        data class NewAccountPin(val pin: String) : StateIntent()
        data class CheckAccountPin(val pin: String?) : StateIntent()
        object OnDemoMode : StateIntent()
    }
}