package org.example;

import java.sql.*;

public class Main {
    public static void main (String[] args) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/hospital", "postgres", System.getenv("password"))) {

            if (conn != null) {
                System.out.println("Connected to the database!");
                printOrtTable(conn);
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

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