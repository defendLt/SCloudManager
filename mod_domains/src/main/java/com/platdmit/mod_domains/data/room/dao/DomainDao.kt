package com.platdmit.mod_domains.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.platdmit.mod_domains.data.room.entity.DbDomain

@Dao
interface DomainDao : BaseDao<DbDomain> {
    @Query("SELECT * FROM dbdomain")
    fun getAllElement(): List<DbDomain>?

    @Query("SELECT * FROM dbdomain WHERE id = :id")
    fun getElement(id: Long): DbDomain?
}