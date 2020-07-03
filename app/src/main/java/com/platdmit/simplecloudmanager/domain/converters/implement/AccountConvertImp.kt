package com.platdmit.simplecloudmanager.domain.converters.implement

import com.platdmit.data.api.models.ApiAccount
import com.platdmit.data.database.entity.DbAccount
import com.platdmit.simplecloudmanager.domain.converters.AccountConverter
import com.platdmit.simplecloudmanager.domain.models.UserAccount

class AccountConvertImp : AccountConverter {
    override fun fromApiToDb(apiAccount: ApiAccount, password: String): DbAccount {
        return DbAccount(
                apiAccount.uuid.toLong(),
                apiAccount.isSubaccount,
                apiAccount.login,
                apiAccount.email,
                password,
                apiAccount.balance,
                apiAccount.vpsLimit
        )
    }

    override fun fromApiToDomain(apiAccount: ApiAccount, password: String): UserAccount {
        return UserAccount(
                apiAccount.uuid.toLong(),
                apiAccount.isSubaccount,
                apiAccount.login,
                password,
                apiAccount.balance,
                apiAccount.vpsLimit
        )
    }

    override fun fromDbToDomain(dbAccount: DbAccount): UserAccount {
        return UserAccount(
                dbAccount.id,
                dbAccount.isSubAccount,
                dbAccount.login,
                dbAccount.pass,
                dbAccount.balance,
                dbAccount.vpsLimit,
                dbAccount.isMain,
                dbAccount.pin.orEmpty()
        )
    }

    override fun fromDomainToDb(userAccount: UserAccount): DbAccount {
        return DbAccount(
                userAccount.id,
                userAccount.subAccount,
                userAccount.login,
                userAccount.email,
                userAccount.pass,
                userAccount.balance,
                userAccount.vpsLimit
        )
    }
}