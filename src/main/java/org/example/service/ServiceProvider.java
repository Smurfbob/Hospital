package org.example.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class ServiceProvider {

    public static final HospitalService HOSPITAL_SERVICE;
    public static final PlaceService PLACE_SERVICE;
    public static final ProfessionService PROFESSION_SERVICE;
    public static final StationProfessionService STATION_PROFESSION_SERVICE;
    public static final StationService STATION_SERVICE;


    static {
        try  {
            final Connection conn = Objects.requireNonNull(DriverManager.getConnection("jdbc:postgresql://127.0.0.1:" + System.getenv("port") + "/hospital", "postgres", System.getenv("password")));
            HOSPITAL_SERVICE = new HospitalService(conn);
            PLACE_SERVICE = new PlaceService(conn);
            PROFESSION_SERVICE = new ProfessionService(conn);
            STATION_PROFESSION_SERVICE = new StationProfessionService(conn);
            STATION_SERVICE = new StationService(conn);
        } catch (SQLException e) {
            throw new RuntimeException(String.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage()));
        }
    }

}
