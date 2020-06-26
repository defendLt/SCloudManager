package com.platdmit.simplecloudmanager.domain.repo

import com.platdmit.simplecloudmanager.data.database.entity.DbUpdateSchedule

interface UpdateScheduleRepo {
    fun getUpdateTimeList(): List<DbUpdateSchedule>?
    fun getUpdateTime(key: String): Long
    fun setUpdateTime(key: String, nextUpdate: Long)
}