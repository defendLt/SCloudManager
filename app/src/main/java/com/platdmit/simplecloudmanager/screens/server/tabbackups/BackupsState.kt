package com.platdmit.simplecloudmanager.screens.server.tabbackups

import com.platdmit.domain.models.Backup

sealed class BackupsState {
    data class Success(val backups: List<Backup>) : BackupsState()
    object Empty : BackupsState()
    object Loading : BackupsState()
    object Error : BackupsState()
}