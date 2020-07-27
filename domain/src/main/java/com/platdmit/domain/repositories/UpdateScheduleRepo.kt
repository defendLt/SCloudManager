package com.platdmit.domain.repositories

import com.platdmit.domain.models.UpdateSchedule

interface UpdateScheduleRepo {
    fun getUpdateTimeList(): List<UpdateSchedule>?
    fun getUpdateTime(key: String): Long
    fun setUpdateTime(key: String, nextUpdate: Long)
}