package com.platdmit.simplecloudmanager.domain.converters.implement;

import android.util.Log;

import com.platdmit.simplecloudmanager.data.api.models.ApiAction;
import com.platdmit.simplecloudmanager.data.database.entity.DbAction;
import com.platdmit.simplecloudmanager.domain.converters.ActionConverter;
import com.platdmit.simplecloudmanager.domain.models.Action;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;
import java.util.List;

public class ActionConvertImp implements ActionConverter {
    @Override
    public DbAction fromApiToDb(ApiAction apiAction) {
        return new DbAction(
                Integer.parseInt(apiAction.getId()),
                apiAction.getStatus(),
                apiAction.getType(),
                apiAction.getStartedAt(),
                apiAction.getCompletedAt(),
                apiAction.getResourceType(),
                apiAction.getInitiator(),
                apiAction.getRegionSlug(),
                Integer.parseInt(apiAction.getResourceId())
        );
    }

    @Override
    public Action fromDbToDomain(DbAction dbAction) {
        return new Action(
                dbAction.getId(),
                dbAction.getStatus(),
                dbAction.getType(),
                actionDateConvert(dbAction.getStartedAt()),
                actionDateConvert(dbAction.getCompletedAt()),
                dbAction.getResourceType(),
                dbAction.getInitiator(),
                dbAction.getRegionSlug(),
                dbAction.getResourceId()
        );
    }

    @Override
    public List<Action> fromDbToDomainList(List<DbAction> dbList) {
        List<Action> convertList = new ArrayList<>();
        for (DbAction dbAction : dbList) {
            convertList.add(fromDbToDomain(dbAction));
        }
        return convertList;
    }

    @Override
    public List<DbAction> fromApiToDbList(List<ApiAction> apiList) {
        List<DbAction> convertList = new ArrayList<>();
        for (ApiAction apiAction : apiList) {
            convertList.add(fromApiToDb(apiAction));
        }
        return convertList;
    }

    private String actionDateConvert(String date) {
        try {
            return DateTimeFormat.forPattern("yyyy-MM-dd, HH:mm").print(new DateTime(date));
        } catch (Exception e){
            Log.d("CONVERT", "Exception: "+e);
        }
        return "";
    }
}
