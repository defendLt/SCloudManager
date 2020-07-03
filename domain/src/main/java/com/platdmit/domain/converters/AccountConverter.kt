package com.platdmit.domain.converters

import com.platdmit.data.api.models.ApiAccount
import com.platdmit.data.database.entity.DbAccount
import com.platdmit.domain.models.UserAccount

interface AccountConverter {
    fun fromApiToDb(apiAccount: ApiAccount, password: String): DbAccount
    fun fromApiToDomain(apiAccount: ApiAccount, password: String): UserAccount
    fun fromDbToDomain(dbAccount: DbAccount): UserAccount
    fun fromDomainToDb(userAccount: UserAccount): DbAccount
}