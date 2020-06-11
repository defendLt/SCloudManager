package com.platdmit.simplecloudmanager.data.database.dao;

import com.platdmit.simplecloudmanager.data.database.entity.DbServer;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ServerDao extends BaseDao<DbServer>{
    @Query("SELECT * FROM dbserver")
    List<DbServer> getAll();

    @Query("SELECT * FROM dbserver WHERE mId = :id")
    DbServer getElement(long id);
}
