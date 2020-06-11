package com.platdmit.simplecloudmanager.data.database.dao;

import com.platdmit.simplecloudmanager.data.database.entity.DbDomain;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface DomainDao extends BaseDao<DbDomain>{
    @Query("SELECT * FROM dbdomain")
    List<DbDomain> getAll();

    @Query("SELECT * FROM dbdomain WHERE mId = :id")
    DbDomain getElement(long id);
}
