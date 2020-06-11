package com.platdmit.simplecloudmanager.data.database.dao;

import com.platdmit.simplecloudmanager.data.database.entity.DbSize;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface SizeDao  extends BaseDao<DbSize>{
    @Query("SELECT * FROM dbsize")
    List<DbSize> getAll();

    @Query("SELECT * FROM dbsize WHERE netId = :id")
    DbSize getElement(String id);
}
