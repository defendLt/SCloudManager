package com.platdmit.simplecloudmanager.domain.converters

import com.platdmit.simplecloudmanager.data.api.models.ApiAccount
import com.platdmit.simplecloudmanager.data.database.entity.DbAccount
import com.platdmit.simplecloudmanager.domain.models.UserAccount

interface AccountConverter {
    fun fromApiToDb(apiAccount: ApiAccount, password: String): DbAccount
    fun fromApiToDomain(apiAccount: ApiAccount, password: String): UserAccount
    fun fromDbToDomain(dbAccount: DbAccount): UserAccount
    fun fromDomainToDb(userAccount: UserAccount): DbAccount
}