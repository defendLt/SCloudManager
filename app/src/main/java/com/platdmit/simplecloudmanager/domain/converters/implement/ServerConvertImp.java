package com.platdmit.simplecloudmanager.domain.converters.implement;

import com.platdmit.simplecloudmanager.data.api.models.ApiServer;
import com.platdmit.simplecloudmanager.data.database.entity.DbServer;
import com.platdmit.simplecloudmanager.domain.converters.ServerConverter;
import com.platdmit.simplecloudmanager.domain.models.Server;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;
import java.util.List;

public class ServerConvertImp implements ServerConverter {
    private static final String DAY = "d.";
    private static final String HOUR = "h.";
    private static final String MINUTE = "m.";
    private static final String HOUR_RU = "час";
    private static final String MONTH_RU = "месяц";
    private static final String CURRENCY_RU = "₽";
    private static final String UPTIME = "Uptime";

    private static final String STATUS_NEW = "создание";
    private static final String STATUS_ON = "работает";
    private static final String STATUS_OFF = "выключен";
    private static final String STATUS_EXEC = "выполнение";

    @Override
    public DbServer fromApiToDb(ApiServer apiServer) {
        return new DbServer(
                apiServer.getId(),
                apiServer.getName(),
                apiServer.getMemory(),
                apiServer.getVcpus(),
                apiServer.getDisk(),
                apiServer.getRegion().getName(),
                apiServer.getImage().getId(),
                apiServer.getBackupPriceHourly(),
                apiServer.getBilling().getPaymentDate(),
                apiServer.getBilling().getPaymentAmount(),
                apiServer.getBilling().getPaymentPeriod(),
                apiServer.getBilling().getTotalHours(),
                apiServer.getBilling().getWorkedHours(),
                apiServer.isLocked(),
                apiServer.getStatus(),
                apiServer.getCreatedAt(),
                apiServer.getStartedFirstAt(),
                apiServer.getStartedAt(),
                apiServer.isIsInstall(),
                apiServer.isIsError(),
                apiServer.getPassword(),
                apiServer.getNetworks().getV4().getIpAddress(),
                apiServer.isMbit200()
        );
    }

    @Override
    public Server fromDbToDomain(DbServer dbServer) {
        return new Server(
                dbServer.getId(),
                dbServer.getName(),
                uptimeConvert(dbServer.getStartedAt()),
                statusConverter(dbServer.getStatus())
        );
    }

    @Override
    public Server fromDbToDomainFull(DbServer dbServer) {
        return new Server(
                dbServer.getId(),
                dbServer.getName(),
                dbServer.getMemory(),
                dbServer.getVcpus(),
                dbServer.getDisk(),
                dbServer.getRegion(),
                dbServer.getBackupPriceHourly(),
                nextPaymentDateConvert(dbServer.getPaymentDate()),
                pricePeriodConverter(dbServer.getPaymentAmount(), dbServer.getPaymentPeriod()),
                uptimeConvert(dbServer.getStartedAt()),
                dbServer.isLocked(),
                statusConverter(dbServer.getStatus()),
                dbServer.getCreatedAt(),
                dbServer.getStartedFirstAt(),
                dbServer.getStartedAt(),
                dbServer.isInstall(),
                dbServer.isError(),
                dbServer.getPassword(),
                dbServer.getV4Ip(),
                dbServer.isMbit200()
        );
    }

    @Override
    public List<Server> fromDbToDomainList(List<DbServer> dbList) {
        List<Server> convertList = new ArrayList<>();
        for (DbServer dbServer : dbList) {
            convertList.add(fromDbToDomain(dbServer));
        }
        return convertList;
    }

    @Override
    public List<DbServer> fromApiToDbList(List<ApiServer> apiList) {
        List<DbServer> convertList = new ArrayList<>();
        for (ApiServer apiServer : apiList) {
            convertList.add(fromApiToDb(apiServer));
        }
        return convertList;
    }

    private String uptimeConvert(String date) {

        DateTime dateTime = new DateTime().minus(new DateTime(date).getMillis());
        Duration uptime = new Duration(dateTime.getMillis());

        long upTimeDay = uptime.getStandardDays();
        long upTimeHours = dateTime.getHourOfDay();

        if (upTimeDay > 0) {
            return UPTIME + ": " + upTimeDay + DAY + " " + upTimeHours + HOUR;
        } else if (upTimeHours > 0) {
            return UPTIME + ": " + upTimeHours + HOUR;
        } else {
            return UPTIME + ":" + dateTime.getMinuteOfHour() + MINUTE;
        }
    }

    private String nextPaymentDateConvert(String date) {
        return DateTimeFormat.forPattern("yyyy-MM-dd HH:mm").print(new DateTime(date));
    }

    private String pricePeriodConverter(String price, String period) {
        return priceConverter(price) + "/" + periodConverter(period);
    }

    private String priceConverter(String price) {
        return Double.valueOf(price) + CURRENCY_RU;
    }

    private String periodConverter(String period) {
        switch (period) {
            case "1 HOUR":
                return HOUR_RU;
            case "1 MONTH":
                return MONTH_RU;
            default:
                return "";
        }
    }

    private String statusConverter(String status) {
        switch (status) {
            case "new":
                return STATUS_NEW;
            case "active":
                return STATUS_ON;
            case "off":
                return STATUS_OFF;
            default:
                return STATUS_EXEC;
        }
    }
}
