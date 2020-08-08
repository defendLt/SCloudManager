package com.platdmit.feature_servers.data.converters

import android.util.Log
import com.platdmit.feature_servers.domain.converters.ActionConverter
import com.platdmit.feature_servers.domain.models.Action
import com.platdmit.feature_servers.data.retrofit.models.ApiAction
import com.platdmit.feature_servers.data.room.entity.DbAction
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import javax.inject.Inject

class ActionConvertImp
@Inject
constructor() : _root_ide_package_.com.platdmit.feature_servers.domain.converters.ActionConverter<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiAction, _root_ide_package_.com.platdmit.feature_servers.domain.models.Action, _root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbAction> {
    override fun fromApiToDb(apiAction: _root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiAction): _root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbAction {
        return _root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbAction(apiAction.id.toInt().toLong(),
                apiAction.status,
                apiAction.type,
                apiAction.startedAt,
                apiAction.completedAt,
                apiAction.resourceType,
                apiAction.initiator,
                apiAction.regionSlug, apiAction.resourceId.toInt().toLong())
    }

    override fun fromDbToDomain(dbAction: _root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbAction): _root_ide_package_.com.platdmit.feature_servers.domain.models.Action {
        return _root_ide_package_.com.platdmit.feature_servers.domain.models.Action(
                dbAction.id,
                dbAction.status,
                dbAction.type,
                actionDateConvert(dbAction.startedAt),
                actionDateConvert(dbAction.completedAt),
                dbAction.resourceType,
                dbAction.initiator,
                dbAction.regionSlug,
                dbAction.resourceId
        )
    }

    override fun fromDbToDomainList(dbList: List<_root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbAction>): List<_root_ide_package_.com.platdmit.feature_servers.domain.models.Action> = dbList.map { fromDbToDomain(it) }

    override fun fromApiToDbList(apiList: List<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiAction>): List<_root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbAction> = apiList.map { fromApiToDb(it) }

    private fun actionDateConvert(date: String): String {
        try {
            return DateTimeFormat.forPattern("yyyy-MM-dd, HH:mm").print(DateTime(date))
        } catch (e: Exception) {
            Log.d("CONVERT", "Exception: $e")
        }
        return ""
    }
}