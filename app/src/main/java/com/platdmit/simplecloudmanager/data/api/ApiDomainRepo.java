package com.platdmit.simplecloudmanager.data.api;

import com.platdmit.simplecloudmanager.data.api.models.ApiDomain;
import com.platdmit.simplecloudmanager.data.api.models.ApiDomainRecord;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface ApiDomainRepo {
    Single<List<ApiDomain>> getDomains();
    Single<ApiDomain> getDomain(long domainId);
    Single<List<ApiDomainRecord>> getDomainRecords(long domainId);
    Single<ApiDomainRecord> getDomainRecord(int domainId, int recordId);
}
