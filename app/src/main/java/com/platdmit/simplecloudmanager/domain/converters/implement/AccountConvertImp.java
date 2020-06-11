package com.platdmit.simplecloudmanager.domain.converters.implement;

import com.platdmit.simplecloudmanager.data.api.models.ApiAccount;
import com.platdmit.simplecloudmanager.data.database.entity.DbAccount;
import com.platdmit.simplecloudmanager.domain.converters.AccountConverter;
import com.platdmit.simplecloudmanager.domain.models.UserAccount;

public class AccountConvertImp implements AccountConverter {
    @Override
    public DbAccount fromApiToDb(ApiAccount apiAccount, String password) {
        return new DbAccount(
                apiAccount.getUuid(),
                apiAccount.isSubaccount(),
                apiAccount.getLogin(),
                apiAccount.getEmail(),
                password,
                apiAccount.getBalance(),
                apiAccount.getVpsLimit()
        );
    }

    @Override
    public UserAccount fromApiToDomain(ApiAccount apiAccount, String password) {
        return new UserAccount(
                apiAccount.getUuid(),
                true,
                apiAccount.isSubaccount(),
                apiAccount.getLogin(),
                password,
                apiAccount.getBalance(),
                apiAccount.getVpsLimit(),
                ""
        );
    }

    @Override
    public UserAccount fromDbToDomain(DbAccount dbAccount) {
        return new UserAccount(
                dbAccount.getId(),
                dbAccount.isMain(),
                dbAccount.isSubAccount(),
                dbAccount.getLogin(),
                dbAccount.getPass(),
                dbAccount.getBalance(),
                dbAccount.getVpsLimit(),
                dbAccount.getPin()
        );
    }

    @Override
    public DbAccount fromDomainToDb(UserAccount userAccount) {
        return new DbAccount(
                userAccount.getId(),
                userAccount.isSubAccount(),
                userAccount.getLogin(),
                userAccount.getEmail(),
                userAccount.getPass(),
                userAccount.getBalance(),
                userAccount.getVpsLimit()
        );
    }
}
