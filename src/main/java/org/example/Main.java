package org.example;

import org.example.service.HospitalService;
import org.example.service.PlaceService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {
    public static void main (String[] args) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:" + System.getenv("port") + "/hospital", "postgres", System.getenv("password"))) {



            if (conn != null) {
                System.out.println("Connected to the database!");
                var hos = new PlaceService(conn);
                hos.getAll().forEach(System.out::println);
                // createTables(conn);
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void createTables(Connection connection) {
        var creationSqlScript = readStringFrom("createTablesHospitalDb.sql");
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(creationSqlScript)) {

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private static String readStringFrom(final String name) {
        return new BufferedReader(new InputStreamReader(Objects.requireNonNull(Thread.currentThread()
                .getContextClassLoader().getResourceAsStream(name)))).lines().collect(Collectors.joining("\n"));
    }

    private static void printOrtTable(Connection conn) {
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Ort")) {

            while (rs.next()) {
                // Retrieve data from each row
                int plz = rs.getInt("plz");
                String name = rs.getString("name");
                String location = rs.getString("region");

                // Print the data
                System.out.println("ID: " + plz);
                System.out.println("Name: " + name);
                System.out.println("Location: " + location);
                System.out.println("--------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}