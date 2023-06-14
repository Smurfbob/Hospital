package org.example;

import org.example.generator.FachrichtungGenerator;
import org.example.generator.KrankenhausGenerator;
import org.example.model.Fachrichtung;
import org.example.model.Krankenhaus;
import org.example.model.Ort;
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

//                List<Krankenhaus> hospitalList = KrankenhausGenerator.generateKrankenhausList(1000);
//                SqlService.generateDataStringForTableKrankenhaus(hospitalList);

                List<Fachrichtung> professionList = FachrichtungGenerator.getAmountOfRandomFachrichtung(10);
                SqlService.generateDataStringForTableFachrichtung(professionList);
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