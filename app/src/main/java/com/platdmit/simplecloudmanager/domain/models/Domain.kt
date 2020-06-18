package com.platdmit.simplecloudmanager.domain.models

class Domain {
    var domainRecords: List<DomainRecord>? = null

    constructor(name: String, type: String);

    constructor(id: Long, name: String, type: String, deleteDate: String, isDelegate: Boolean);
}