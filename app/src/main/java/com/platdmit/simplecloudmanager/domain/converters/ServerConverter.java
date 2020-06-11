package com.platdmit.simplecloudmanager.domain.converters;

import com.platdmit.simplecloudmanager.data.api.models.ApiServer;
import com.platdmit.simplecloudmanager.data.database.entity.DbServer;
import com.platdmit.simplecloudmanager.domain.models.Server;

import java.util.List;

public interface ServerConverter {
    DbServer fromApiToDb(ApiServer apiServer);
    Server fromDbToDomain(DbServer dbServer);
    Server fromDbToDomainFull(DbServer dbServer);
    List<Server> fromDbToDomainList(List<DbServer> dbList);
    List<DbServer> fromApiToDbList(List<ApiServer> apiList);
}
