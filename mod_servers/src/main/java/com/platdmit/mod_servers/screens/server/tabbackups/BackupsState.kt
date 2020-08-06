package com.platdmit.mod_servers.screens.server.tabbackups

import com.platdmit.mod_servers.domain.models.Backup

sealed class BackupsState {
    data class Success(val backups: List<Backup>) : BackupsState()
    object Empty : BackupsState()
    object Loading : BackupsState()
    object Error : BackupsState()
}