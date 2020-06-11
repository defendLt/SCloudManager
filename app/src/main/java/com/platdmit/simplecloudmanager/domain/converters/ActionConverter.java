package com.platdmit.simplecloudmanager.domain.converters;

import com.platdmit.simplecloudmanager.data.api.models.ApiAction;
import com.platdmit.simplecloudmanager.data.database.entity.DbAction;
import com.platdmit.simplecloudmanager.domain.models.Action;

import java.util.List;

public interface ActionConverter {
    DbAction fromApiToDb(ApiAction apiAction);
    Action fromDbToDomain(DbAction dbAction);
    List<Action> fromDbToDomainList(List<DbAction> dbList);
    List<DbAction> fromApiToDbList(List<ApiAction> apiList);
}
