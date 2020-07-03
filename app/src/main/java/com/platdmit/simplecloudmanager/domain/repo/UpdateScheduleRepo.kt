package com.platdmit.simplecloudmanager.domain.repo

import com.platdmit.data.database.entity.DbUpdateSchedule

interface UpdateScheduleRepo {
    fun getUpdateTimeList(): List<DbUpdateSchedule>?
    fun getUpdateTime(key: String): Long
    fun setUpdateTime(key: String, nextUpdate: Long)
}