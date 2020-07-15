package com.platdmit.domain.repo

interface UpdateScheduleRepo<DbModel> {
    fun getUpdateTimeList(): List<DbModel>?
    fun getUpdateTime(key: String): Long
    fun setUpdateTime(key: String, nextUpdate: Long)
}