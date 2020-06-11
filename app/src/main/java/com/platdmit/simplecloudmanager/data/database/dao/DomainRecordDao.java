package com.platdmit.simplecloudmanager.data.database.dao;

import com.platdmit.simplecloudmanager.data.database.entity.DbDomainRecord;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface DomainRecordDao extends BaseDao<DbDomainRecord>{
    @Query("SELECT * FROM dbdomainrecord WHERE mParentDomain = :id")
    List<DbDomainRecord> getRecordsForDomain(long id);

    @Query("SELECT * FROM dbdomainrecord WHERE mId = :id")
    DbDomainRecord getRecord(long id);
}
