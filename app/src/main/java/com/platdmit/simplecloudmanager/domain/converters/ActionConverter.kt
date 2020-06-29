package com.platdmit.simplecloudmanager.domain.converters

import com.platdmit.simplecloudmanager.data.api.models.ApiAction
import com.platdmit.simplecloudmanager.data.database.entity.DbAction
import com.platdmit.simplecloudmanager.domain.models.Action

interface ActionConverter {
    fun fromApiToDb(apiAction: ApiAction): DbAction
    fun fromDbToDomain(dbAction: DbAction): Action
    fun fromDbToDomainList(dbList: List<DbAction>): List<Action>
    fun fromApiToDbList(apiList: List<ApiAction>): List<DbAction>
}