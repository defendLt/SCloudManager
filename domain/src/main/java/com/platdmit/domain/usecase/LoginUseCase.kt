package com.platdmit.domain.usecase

import com.platdmit.domain.models.UserAccount
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

interface LoginUseCase {
    fun getActiveAccount() : Single<UserAccount>
    fun addAccount(login: String, pass: String) : Maybe<UserAccount>
    fun addAccountPin(account: UserAccount) : Completable
    fun checkAccountPin(pin: String?, account: UserAccount) : Completable
    fun successAuthAccount() : Completable
    fun startDemoAccount(): Completable
}