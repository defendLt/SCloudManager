package com.platdmit.feature_servers.domain.models

data class Image(
        val id: Int,
        val netId: Long,
        val name: String,
        val logo: Int,
        val min_disk_size: Int,
        val min_memory_size: Int
)