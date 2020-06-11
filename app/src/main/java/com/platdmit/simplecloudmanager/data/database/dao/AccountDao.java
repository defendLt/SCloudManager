package com.platdmit.simplecloudmanager.data.database.dao;

import com.platdmit.simplecloudmanager.data.database.entity.DbAccount;
import com.platdmit.simplecloudmanager.data.database.entity.DbServer;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface AccountDao extends BaseDao<DbAccount>{
    @Query("SELECT * FROM dbaccount")
    List<DbAccount> getAll();

    @Query("SELECT * FROM dbaccount WHERE mId = :id")
    DbAccount getElement(long id);

    @Query("SELECT * FROM dbaccount WHERE mMain = 1")
    DbAccount getBaseAccount();
}
