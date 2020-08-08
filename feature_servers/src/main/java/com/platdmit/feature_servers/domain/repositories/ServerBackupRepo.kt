package com.platdmit.feature_servers.domain.repositories

import com.platdmit.feature_servers.domain.models.Backup
import io.reactivex.rxjava3.core.Observable

interface ServerBackupRepo {
    fun getServerBackups(id: Long): Observable<List<_root_ide_package_.com.platdmit.feature_servers.domain.models.Backup>>
}