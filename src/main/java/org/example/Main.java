package org.example;

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

                Ort place = new Ort(12345, "bla", "blubb");
                Ort place1 = new Ort(12346, "bla", "blubb");
                Ort place2 = new Ort(12347, "bla", "blubb");
                List<Ort> placeList = List.of(place, place1, place2);
                sqlservice.generateDataStringForTableOrt(placeList);
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