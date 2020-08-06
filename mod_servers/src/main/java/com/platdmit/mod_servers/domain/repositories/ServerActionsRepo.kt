package com.platdmit.mod_servers.domain.repositories

import com.platdmit.mod_servers.domain.models.Action
import io.reactivex.rxjava3.core.Observable

interface ServerActionsRepo {
    fun getServerActions(id: Long): Observable<List<Action>>
}