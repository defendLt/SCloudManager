package com.platdmit.domain.repo.implement

import com.platdmit.data.database.DbManager
import com.platdmit.data.database.dao.UpdateScheduleDao
import com.platdmit.data.database.entity.DbUpdateSchedule
import com.platdmit.domain.repo.UpdateScheduleRepo
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers

class UpdateScheduleRepImp(dbManager: DbManager) : UpdateScheduleRepo {
    private val mDbManager: UpdateScheduleDao = dbManager.mUpdateScheduleDao();
    override fun getUpdateTimeList(): List<DbUpdateSchedule>? {
        return mDbManager.getScheduleAll()
    }

    override fun getUpdateTime(key: String): Long {
        return mDbManager.getScheduleForName(key)?.nextUpdate ?: 0
    }

    override fun setUpdateTime(key: String, nextUpdate: Long) {
        Completable.fromAction {mDbManager.insert(DbUpdateSchedule(key, nextUpdate))}.observeOn(Schedulers.io()).subscribe()
    }

    companion object {
        private val TAG = UpdateScheduleRepImp::class.java.simpleName
    }
}