package com.platdmit.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.platdmit.data.database.entity.DbDomainRecord

@Dao
interface DomainRecordDao : BaseDao<DbDomainRecord> {
    @Query("SELECT * FROM dbdomainrecord WHERE parentDomain = :id")
    fun getRecordsForDomain(id: Long): List<DbDomainRecord>?

    @Query("SELECT * FROM dbdomainrecord WHERE id = :id")
    fun getRecord(id: Long): DbDomainRecord?
}