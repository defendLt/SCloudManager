package com.platdmit.feature_domains.domain.models

data class Domain(val id: Long, val name: String, val type: String, val deleteDate: String, val isDelegate: Boolean) {
    var domainRecords: List<DomainRecord>? = null
}