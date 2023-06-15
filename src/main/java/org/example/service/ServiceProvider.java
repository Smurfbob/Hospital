package org.example.service;

import org.example.utils.DatabaseUtils;

import java.sql.Connection;

public class ServiceProvider {


    public static final KrankenhausService HOSPITAL_SERVICE;
    public static final PlaceService PLACE_SERVICE;
    public static final ProfessionService PROFESSION_SERVICE;
    public static final StationProfessionService STATION_PROFESSION_SERVICE;
    public static final StationService STATION_SERVICE;


    static {
        final Connection conn = DatabaseUtils.requestDatabaseConnection();
        HOSPITAL_SERVICE = new KrankenhausService(conn);
        PLACE_SERVICE = new PlaceService(conn);
        PROFESSION_SERVICE = new ProfessionService(conn);
        STATION_PROFESSION_SERVICE = new StationProfessionService(conn);
        STATION_SERVICE = new StationService(conn);
    }

}
