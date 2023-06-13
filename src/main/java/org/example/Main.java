package org.example;

import org.example.service.HospitalService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {
    public static void main (String[] args) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:" + System.getenv("port") + "/hospital", "postgres", System.getenv("password"))) {

            new HospitalService(conn);
            if (conn != null) {
                System.out.println("Connected to the database!");
                SqlService sqlservice = new SqlService(conn);
                sqlservice.createTables();

                List<String> stringList = List.of("Eins", "Zwei");
                List<Object> objectList = List.of();
                //sqlservice.mergeTestData("bla", );
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





//    private static void printOrtTable(Connection conn) {
//        try (Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery("SELECT * FROM Ort")) {
//
//            while (rs.next()) {
//                // Retrieve data from each row
//                int plz = rs.getInt("plz");
//                String name = rs.getString("name");
//                String location = rs.getString("region");
//
//                // Print the data
//                System.out.println("ID: " + plz);
//                System.out.println("Name: " + name);
//                System.out.println("Location: " + location);
//                System.out.println("--------------------------");
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}