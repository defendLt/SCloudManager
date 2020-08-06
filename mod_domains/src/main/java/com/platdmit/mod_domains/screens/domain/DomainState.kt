package com.platdmit.mod_domains.screens.domain

import com.platdmit.mod_domains.domain.models.Domain

sealed class DomainState {
    data class Success(val domain: Domain) : DomainState()
    object Empty : DomainState()
    object Loading : DomainState()
    object Error : DomainState()
}