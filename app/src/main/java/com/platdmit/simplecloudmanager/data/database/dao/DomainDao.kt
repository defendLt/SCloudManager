package com.platdmit.simplecloudmanager.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.platdmit.simplecloudmanager.data.database.entity.DbDomain

@Dao
interface DomainDao : BaseDao<DbDomain> {
    @Query("SELECT * FROM dbdomain")
    fun getAllElement(): List<DbDomain>?

    @Query("SELECT * FROM dbdomain WHERE id = :id")
    fun getElement(id: Long): DbDomain?
}