package com.platdmit.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.platdmit.data.database.entity.DbAccount

@Dao
interface AccountDao : BaseDao<DbAccount> {
    @Query("SELECT * FROM dbaccount")
    fun getAllAccounts(): List<DbAccount>?

    @Query("SELECT * FROM dbaccount WHERE id = :id")
    fun getElement(id: Long): DbAccount?

    @Query("SELECT * FROM dbaccount WHERE isMain = 1")
    fun getBaseAccount(): DbAccount?
}