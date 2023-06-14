package org.example;

import org.example.generator.*;
import org.example.model.*;
import org.example.service.HospitalService;
import org.example.utils.DatabaseUtils;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main (String[] args) {
        try (Connection conn = DatabaseUtils.requestDatabaseConnection()) {
            if (conn != null) {
                System.out.println("Connected to the database!");
                SqlService sqlservice = new SqlService(conn);
                SqlService.createTables();
                SqlService.deleteAllTableValues();

                List<Ort> locationList = OrtGenerator.generateData(100);
                List<Fachrichtung> professionList = FachrichtungGenerator.create30FachrichtungenListe();

                SqlService.generateDataStringForTableOrt(locationList);
                SqlService.generateDataStringForTableFachrichtung(professionList);

                List<Krankenhaus> hospitalList = KrankenhausGenerator.generateKrankenhausList(100);
                SqlService.generateDataStringForTableKrankenhaus(hospitalList);

                List<Station> stationList = StationGenerator.getAmountOfRandomStationFachrichtung(hospitalList, 12);
                SqlService.generateDataStringForTableStation(stationList);

                List<FachrichtungsStation> fachrichtungsStationList = FachrichtungStationGenerator.getAmountOfRandomStationFachrichtung(
                        professionList, stationList);
                SqlService.generateDataStringForTableFachrichtungStation(fachrichtungsStationList);

            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}