package com.platdmit.simplecloudmanager.domain.repo;

import com.platdmit.simplecloudmanager.domain.models.Domain;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface DomainBaseRepo {
    Observable<List<Domain>> getDomains();
    Observable<Domain> getDomain(long id);
    void nextUpdate();
}
