package com.platdmit.mod_login.data.converters

import com.platdmit.mod_login.data.retrofit.models.ApiAccount
import com.platdmit.mod_login.domain.converters.AccountConverter
import com.platdmit.mod_login.domain.models.UserAccount
import com.platdmit.mod_login.data.room.entity.DbAccount
import javax.inject.Inject

class AccountConvertImp
@Inject
constructor() : AccountConverter<ApiAccount, UserAccount, DbAccount> {
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
                apiAccount.login,
                password,
                apiAccount.balance,
                apiAccount.vpsLimit,
                apiAccount.isSubaccount,
                "",
                false
        )
    }

    override fun fromDbToDomain(dbAccount: DbAccount): UserAccount {
        return UserAccount(
                dbAccount.id,
                dbAccount.login,
                dbAccount.pass,
                dbAccount.balance,
                dbAccount.vpsLimit,
                dbAccount.isSubAccount,
                dbAccount.pin.orEmpty(),
                dbAccount.isMain
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