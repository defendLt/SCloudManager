package com.platdmit.domain.repositories

import com.platdmit.domain.models.UserAccount
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface AccountRepo {
    fun getActiveAccount(): Single<UserAccount>
    fun getPrepareAccountInfo(login: String, pass: String): Single<UserAccount>
    fun addAccountPin(account: UserAccount): Completable
}