package com.platdmit.domain.usecase.impl

import com.platdmit.domain.models.UserAccount
import com.platdmit.domain.repositories.AccountRepo
import com.platdmit.domain.usecase.LoginUseCase
import com.platdmit.domain.utilities.ActualApiKeyServiceManager
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.mindrot.BCrypt
import javax.inject.Inject

class LoginUseCaseImpl
@Inject
constructor(
        private val accountRepo: AccountRepo,
        private val actualApiKeyServiceManager: ActualApiKeyServiceManager
) : LoginUseCase {
    override fun getActiveAccount(): Single<UserAccount> {
        return accountRepo.getActiveAccount()
                .subscribeOn(Schedulers.newThread())
    }

    override fun addAccount(login: String, pass: String): Maybe<UserAccount> {
        return accountRepo.getPrepareAccountInfo(login, pass)
                .toMaybe()
    }

    override fun addAccountPin(account: UserAccount): Completable {
        return accountRepo.addAccountPin(account)
                .doOnComplete {
                    actualApiKeyServiceManager.setActiveAccount(account)
                }
                .subscribeOn(Schedulers.newThread())
    }

    override fun checkAccountPin(pin: String?, account: UserAccount): Completable {
        return Completable.create{
            if (BCrypt.checkpw(pin, account.pin)) {
                it.onComplete()
            } else {
                it.onError(throw Exception("Fall"))
            }
        }
    }

    override fun successAuthAccount(): Completable {
        return actualApiKeyServiceManager.getAccountStatus()
                .observeOn(Schedulers.newThread())
                .onErrorComplete()
    }

    override fun startDemoAccount(): Completable {
        return Completable.create{
            actualApiKeyServiceManager.startDemoMode()
            it.onComplete()
        }
    }
}