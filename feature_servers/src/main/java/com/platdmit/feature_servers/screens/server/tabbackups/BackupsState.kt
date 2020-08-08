package com.platdmit.feature_servers.screens.server.tabbackups

import com.platdmit.feature_servers.domain.models.Backup

sealed class BackupsState {
    data class Success(val backups: List<_root_ide_package_.com.platdmit.feature_servers.domain.models.Backup>) : BackupsState()
    object Empty : BackupsState()
    object Loading : BackupsState()
    object Error : BackupsState()
}