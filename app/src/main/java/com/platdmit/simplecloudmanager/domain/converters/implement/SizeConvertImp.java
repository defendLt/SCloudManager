package com.platdmit.simplecloudmanager.domain.converters.implement;

import com.platdmit.simplecloudmanager.data.api.models.ApiSize;
import com.platdmit.simplecloudmanager.data.database.entity.DbSize;
import com.platdmit.simplecloudmanager.domain.converters.SizeConverter;
import com.platdmit.simplecloudmanager.domain.models.Size;

import java.util.ArrayList;
import java.util.List;

public class SizeConvertImp implements SizeConverter {
    @Override
    public DbSize fromApiToDb(ApiSize apiSize) {
        return new DbSize(
                apiSize.getId(),
                apiSize.getSlug(),
                apiSize.getMemory(),
                apiSize.getVcpus(),
                apiSize.getDisk(),
                apiSize.getTransfer(),
                apiSize.getPriceMonthly(),
                apiSize.getPriceHourly(),
                apiSize.getLinked(),
                apiSize.getMain(),
                apiSize.isTest(),
                apiSize.isArchive()
        );
    }

    @Override
    public Size fromDbToDomain(DbSize dbSize) {
        return new Size(
                dbSize.getId(),
                dbSize.getNetId(),
                dbSize.getSlug(),
                dbSize.getMemory(),
                dbSize.getVcpus(),
                dbSize.getDisk(),
                dbSize.getTransfer(),
                dbSize.getPriceMonthly(),
                dbSize.getPriceHourly(),
                dbSize.getLinked(),
                dbSize.getMain(),
                dbSize.isTest(),
                dbSize.isArchive()
        );
    }

    @Override
    public List<Size> fromDbToDomainList(List<DbSize> dbList) {
        List<Size> convertList = new ArrayList<>();
        for (DbSize dbSize : dbList) {
            convertList.add(fromDbToDomain(dbSize));
        }
        return convertList;
    }

    @Override
    public List<DbSize> fromApiToDbList(List<ApiSize> apiList) {
        List<DbSize> convertList = new ArrayList<>();
        for (ApiSize apiSize : apiList) {
            convertList.add(fromApiToDb(apiSize));
        }
        return convertList;
    }
}
