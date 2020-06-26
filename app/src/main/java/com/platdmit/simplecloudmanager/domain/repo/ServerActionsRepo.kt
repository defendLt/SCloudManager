package com.platdmit.simplecloudmanager.domain.repo

import com.platdmit.simplecloudmanager.domain.models.Action
import io.reactivex.rxjava3.core.Observable

interface ServerActionsRepo {
    fun getServerActions(id: Long): Observable<List<Action>>
}