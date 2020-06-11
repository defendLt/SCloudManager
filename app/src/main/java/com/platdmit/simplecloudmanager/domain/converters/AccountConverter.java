package com.platdmit.simplecloudmanager.domain.converters;

import com.platdmit.simplecloudmanager.data.api.models.ApiAccount;
import com.platdmit.simplecloudmanager.data.database.entity.DbAccount;
import com.platdmit.simplecloudmanager.domain.models.UserAccount;

public interface AccountConverter {
    DbAccount fromApiToDb(ApiAccount apiAccount, String password);
    UserAccount fromApiToDomain(ApiAccount apiAccount, String password);
    UserAccount fromDbToDomain(DbAccount dbAccount);
    DbAccount fromDomainToDb(UserAccount userAccount);
}
