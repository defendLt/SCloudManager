package com.platdmit.simplecloudmanager.domain.models

data class Action(val id: Long, val status: String, val type: String, val startedAt: String, val completedAt: String, val resourceType: String, val initiator: String, val regionSlug: String, val resourceId: Long)