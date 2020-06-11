package com.platdmit.simplecloudmanager.data.database.dao;

import java.util.List;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

public interface BaseDao<Db> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Db dbElement);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<Db> dbElements);

    @Update
    void update(Db dbElement);

    @Update
    void updateAll(List<Db> dbElements);

    @Delete
    void delete(Db dbElement);
}
