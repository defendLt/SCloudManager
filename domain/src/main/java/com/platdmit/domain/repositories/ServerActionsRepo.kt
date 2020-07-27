package com.platdmit.domain.repositories

import com.platdmit.domain.models.Action
import io.reactivex.rxjava3.core.Observable

interface ServerActionsRepo {
    fun getServerActions(id: Long): Observable<List<Action>>
}