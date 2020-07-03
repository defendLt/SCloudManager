package com.platdmit.domain.models

data class Size(val id: Long, val netId: String, val slug: String, val memory: String, val vcpus: String,
                val disk: String, val transfer: String, val priceMonthly: String, val priceHourly: String,
                val linked: String, val main: String, val isTest: Boolean, val isArchive: Boolean
)