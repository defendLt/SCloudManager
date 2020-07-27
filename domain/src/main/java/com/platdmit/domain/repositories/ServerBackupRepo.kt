package com.platdmit.domain.repositories

import com.platdmit.domain.models.Backup
import io.reactivex.rxjava3.core.Observable

interface ServerBackupRepo {
    fun getServerBackups(id: Long): Observable<List<Backup>>
}