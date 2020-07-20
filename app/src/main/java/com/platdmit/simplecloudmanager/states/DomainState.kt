package com.platdmit.simplecloudmanager.states

import com.platdmit.domain.models.Domain

sealed class DomainState {
    data class Success(val domain: Domain) : DomainState()
    object Empty : DomainState()
    object Loading : DomainState()
    object Error : DomainState()
}