package com.platdmit.mod_servers.domain.repositories

import com.platdmit.mod_servers.domain.models.Backup
import io.reactivex.rxjava3.core.Observable

interface ServerBackupRepo {
    fun getServerBackups(id: Long): Observable<List<Backup>>
}