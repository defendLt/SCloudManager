package com.platdmit.mod_login.domain.repositories.api

import io.reactivex.rxjava3.core.Single

interface ApiAccountRepo<Auth, Account> {
    fun getApiKey(login: String, pass: String): Single<Auth>
    fun getActiveAccount(): Single<Account>
}