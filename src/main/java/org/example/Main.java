package org.example;

import org.example.generator.*;
import org.example.model.*;
import org.example.service.PlaceService;
import org.example.utils.DatabaseUtils;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main (String[] args) {

        try (Connection conn = DatabaseUtils.requestDatabaseConnection()) {
            if (conn != null) {
                System.out.println("DESC ORDER =======");
                new PlaceService(conn).getSortedData("DESC");
                System.out.println("ASC ORDER =======");
                new PlaceService(conn).getSortedData("ASC");
//                System.out.println("Connected to the database!");
//                SqlService.createTables();
//                SqlService.deleteAllTableValues();
//
//                List<Ort> locationList = OrtGenerator.generateData(100);
//                List<Fachrichtung> professionList = FachrichtungGenerator.create30FachrichtungenListe();
//
//                SqlService.generateDataStringForTableOrt(locationList);
//                SqlService.generateDataStringForTableFachrichtung(professionList);
//
//                List<Krankenhaus> hospitalList = KrankenhausGenerator.generateKrankenhausList(100);
//                SqlService.generateDataStringForTableKrankenhaus(hospitalList);
//
//                List<Station> stationList = StationGenerator.getAmountOfRandomStationFachrichtung(hospitalList, 12);
//                SqlService.generateDataStringForTableStation(stationList);
//
//                List<FachrichtungsStation> fachrichtungsStationList = FachrichtungStationGenerator.getAmountOfRandomStationFachrichtung(
//                        professionList, stationList);
//                SqlService.generateDataStringForTableFachrichtungStation(fachrichtungsStationList);
//
//            } else {
//                System.out.println("Failed to make connection!");
//            }
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}