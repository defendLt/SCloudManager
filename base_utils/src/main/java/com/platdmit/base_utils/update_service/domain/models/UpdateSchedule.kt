package com.platdmit.base_utils.update_service.domain.models

data class UpdateSchedule(
        val name: String,
        val nextUpdate: Long,
        var updateInterval: Long,
        var lastUpdate: Long
)