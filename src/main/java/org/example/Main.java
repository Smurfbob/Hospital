package org.example;

import org.example.generator.*;
import org.example.model.*;
import org.example.service.HospitalService;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main (String[] args) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:" + System.getenv("port") + "/hospital",
                "postgres", System.getenv("password"))) {

            new HospitalService(conn);
            if (conn != null) {
                System.out.println("Connected to the database!");
                SqlService sqlservice = new SqlService(conn);

                List<Ort> locationList = OrtGenerator.generateData(500);
                List<Fachrichtung> professionList = FachrichtungGenerator.getAmountOfRandomFachrichtung(30);

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