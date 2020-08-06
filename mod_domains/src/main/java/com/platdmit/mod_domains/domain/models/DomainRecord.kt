package com.platdmit.mod_domains.domain.models

data class DomainRecord(val id: Long, val priority: String, val port: String, val weight: String,
                        val ttl: String, val type: String, val name: String, val data: String) {
    var parentDomain = 0
}