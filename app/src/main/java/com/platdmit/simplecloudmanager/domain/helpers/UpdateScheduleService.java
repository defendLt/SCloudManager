package com.platdmit.simplecloudmanager.domain.helpers;

import org.joda.time.DateTime;

public interface UpdateScheduleService{
    void setUpdateTime(String key, int timer);
    void setDefaultUpdateTime(String key);
    boolean getActualStatus(String key);
}
