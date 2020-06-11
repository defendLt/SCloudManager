package com.platdmit.simplecloudmanager.domain.converters.implement;

import com.platdmit.simplecloudmanager.data.api.models.ApiLoadAverage;
import com.platdmit.simplecloudmanager.data.database.entity.DbLoadAverage;
import com.platdmit.simplecloudmanager.domain.converters.LoadAverageConverter;
import com.platdmit.simplecloudmanager.domain.models.LoadAverage;

import java.util.ArrayList;
import java.util.List;

public class LoadAverageConvertImp implements LoadAverageConverter {
    private static final String CPU_NAME = "Процессор";
    private static final String RAM_NAME = "Память";
    private static final String ROM_NAME = "Дисковое хранилище";
    private static final String VAL_PERCENT = "%";
    private static final String VAL_SIZE = "Gb";

    @Override
    public List<DbLoadAverage> fromApiToDb(ApiLoadAverage apiLoadAverage, long serverId) {
        List<DbLoadAverage> loadAverageList = new ArrayList<>();
        loadAverageList.add(new DbLoadAverage(
                CPU_NAME,
                serverId,
                apiLoadAverage.cPU.getTotal(),
                apiLoadAverage.cPU.getHourly() + VAL_PERCENT,
                apiLoadAverage.cPU.getHourly()
        ));

        loadAverageList.add(new DbLoadAverage(
                RAM_NAME,
                serverId,
                apiLoadAverage.rAM.getTotal(),
                apiLoadAverage.rAM.getHourly() + VAL_PERCENT,
                apiLoadAverage.rAM.getHourly()
        ));

        loadAverageList.add(new DbLoadAverage(
                ROM_NAME,
                serverId,
                apiLoadAverage.disk.getTotal(),
                apiLoadAverage.disk.getFree() + VAL_SIZE,
                getFreePercent(apiLoadAverage.disk.getTotal(), apiLoadAverage.disk.getFree())
        ));

        return loadAverageList;
    }

    @Override
    public List<LoadAverage> fromApiToDomain(ApiLoadAverage apiLoadAverage) {
        List<LoadAverage> loadAverageList = new ArrayList<>();
        loadAverageList.add(new LoadAverage(
                CPU_NAME,
                apiLoadAverage.cPU.getTotal(),
                apiLoadAverage.cPU.getHourly() + VAL_PERCENT,
                apiLoadAverage.cPU.getHourly()
        ));

        loadAverageList.add(new LoadAverage(
                RAM_NAME,
                apiLoadAverage.rAM.getTotal(),
                apiLoadAverage.rAM.getHourly() + VAL_PERCENT,
                apiLoadAverage.rAM.getHourly()
        ));

        loadAverageList.add(new LoadAverage(
                ROM_NAME,
                apiLoadAverage.disk.getTotal(),
                apiLoadAverage.disk.getFree() + VAL_SIZE,
                getFreePercent(apiLoadAverage.disk.getTotal(), apiLoadAverage.disk.getFree())
        ));
        return loadAverageList;
    }

    @Override
    public List<LoadAverage> fromDbToDomain(List<DbLoadAverage> dbLoadAverages) {
        List<LoadAverage> loadAverageList = new ArrayList<>();
        for (DbLoadAverage dbLoadAverage : dbLoadAverages){
            loadAverageList.add(new LoadAverage(
                    dbLoadAverage.getName(),
                    dbLoadAverage.getTotal(),
                    dbLoadAverage.getValue(),
                    dbLoadAverage.getPercent()
            ));
        }
        return loadAverageList;
    }


    private double getFreePercent(String total, String free) {
        try {
            double numTotal = Double.parseDouble(total);
            double numFree = Double.parseDouble(free);
            return numFree / numTotal * 100;
        } catch (Exception e) {
            return 0;
        }
    }
}
