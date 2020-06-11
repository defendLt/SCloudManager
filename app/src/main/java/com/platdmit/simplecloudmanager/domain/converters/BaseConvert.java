package com.platdmit.simplecloudmanager.domain.converters;

public interface BaseConvert {

    public <API,DB>API fromApiToDb(DB apiElement);

    public <DB,D>D fromDbToDomain(DB DbElement);

    public <API,D>D fromApiToDomain(API apiElement);

}
