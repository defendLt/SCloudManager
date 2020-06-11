package com.platdmit.simplecloudmanager.data.database;

import com.platdmit.simplecloudmanager.data.database.dao.*;
import com.platdmit.simplecloudmanager.data.database.entity.*;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {DbAccount.class, DbDomain.class, DbDomainRecord.class,
        DbServer.class, DbAction.class, DbBackup.class, DbLoadAverage.class,
        DbStatistic.class, DbUpdateSchedule.class}, version = 1, exportSchema = false)
public abstract class DbManager extends RoomDatabase {
    public abstract AccountDao mAccountDao();
    public abstract DomainDao mDomainDao();
    public abstract DomainRecordDao mDomainRecordDao();
    public abstract ServerDao mServerDao();
    public abstract ActionDao mActionDao();
    public abstract BackupDao mBackupDao();
    public abstract LoadAverageDao mLoadAverageDao();
    public abstract StatisticDao mStatisticDao();
    public abstract UpdateScheduleDao mUpdateScheduleDao();
}
