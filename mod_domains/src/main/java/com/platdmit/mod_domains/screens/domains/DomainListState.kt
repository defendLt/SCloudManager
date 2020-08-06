package com.platdmit.mod_domains.screens.domains

import com.platdmit.mod_domains.domain.models.Domain

sealed class DomainListState {
    data class Success(val domains: List<Domain>) : DomainListState()
    object Empty : DomainListState()
    object Loading : DomainListState()
    object Error : DomainListState()
}