package org.example;

import org.example.model.Hospital;
import org.example.model.Place;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
public class SqlService {
    private static Connection _connection;
    public SqlService(Connection connection) {
        _connection = connection;
    }

    public static void createTables() {
        String sqlCreationScript = readStringFrom("createTablesHospitalDb.sql");
        try (Statement statement = _connection.createStatement();
             ResultSet rs = statement.executeQuery(sqlCreationScript)) {

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void generateDataStringForTableOrt(List<Place> placeList) {
        String formattedDataForSql = "";

        for (int i = 0; i < placeList.size(); i++) {
            formattedDataForSql += String.format("(%d, \'%s', \'%s')",
                    placeList.get(i).getPLZ(), placeList.get(i).getName(), placeList.get(i).getRegion());
            if (i < placeList.size()-1) {
                formattedDataForSql += ", ";
            }
        }

        insertTestData("ort", "plz, name, region", formattedDataForSql);
    }:wq

    public static void generateDataStringForTableKrankenhaus(List<Hospital> hospitalList) {
        String formattedDataForSql = "";

//        hospitalList.get(1).get
//
//        for (int i = 0; i < hospitalList.size(); i++) {
//            formattedDataForSql += String.format("(%d, \'%s', \'%s')",
//                    hospitalList.get(i).getkkhID(), placeList.get(i).getName(), placeList.get(i).getRegion());
//            if (i < placeList.size()-1) {
//                formattedDataForSql += ", ";
//            }
//        }

        insertTestData("ort", "plz, name, region", formattedDataForSql);
    }

    private static void insertTestData(String tablename, String columns, String values) {
        String sqlQuery = String.format("INSERT INTO %s (%s)%n VALUES %s%n;",
                //Variablen für SQL-Query
                tablename, columns, values);

        try (Statement statement = _connection.createStatement();
             ResultSet rs = statement.executeQuery(sqlQuery);) {

        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    private static String readStringFrom(final String name) {
        return new BufferedReader(new InputStreamReader(Objects.requireNonNull(Thread.currentThread()
                .getContextClassLoader().getResourceAsStream(name)))).lines().collect(Collectors.joining("\n"));
    }
}
