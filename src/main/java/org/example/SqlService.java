package org.example;

import org.example.model.*;
import org.example.utils.DatabaseUtils;

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

    private static final Connection _connection = DatabaseUtils.requestDatabaseConnection();

    public static void createTables() {
        String sqlCreationScript = readStringFrom("createTablesHospitalDb.sql");

        try {
            Statement statement = _connection.createStatement();
            ResultSet rs = statement.executeQuery(sqlCreationScript);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void deleteAllTableValues() {
        String sqlDeletionScript = readStringFrom("deleteTablesHospitalDb.sql");

        try {
            Statement statement = _connection.createStatement();
            ResultSet rs = statement.executeQuery(sqlDeletionScript);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void generateDataStringForTableOrt(List<Ort> locationList) {
        StringBuilder formattedDataForSql = new StringBuilder();

        for (int i = 0; i < locationList.size(); i++) {
            formattedDataForSql.append(String.format("(%d, '%s', '%s')",
                    locationList.get(i).getPlz(), locationList.get(i).getName(), locationList.get(i).getRegion()));
            if (i < locationList.size() - 1) {
                formattedDataForSql.append(", ");
            }
        }
        insertTestData("ort", "plz, name, region", formattedDataForSql.toString());
    }

    public static void generateDataStringForTableKrankenhaus(List<Krankenhaus> hospitalList) {
        StringBuilder formattedDataForSql = new StringBuilder();

        for (int i = 0; i < hospitalList.size(); i++) {
            formattedDataForSql.append(String.format("(%d, '%s', '%s', '%s', %d)",
                    hospitalList.get(i).getKrankehausId(), hospitalList.get(i).getName(), hospitalList.get(i).getStrasse(),
                    hospitalList.get(i).getAnsprechpartner(), hospitalList.get(i).getPlz()));
            if (i < hospitalList.size() - 1) {
                formattedDataForSql.append(", ");
            }
        }

        insertTestData("krankenhaus", "krankenhaus_id, name, strasse, ansprechpartner, plz", formattedDataForSql.toString());
    }

    public static void generateDataStringForTableFachrichtung(List<Fachrichtung> professionList) {
        StringBuilder formattedDataForSql = new StringBuilder();

        for (int i = 0; i < professionList.size(); i++) {
            formattedDataForSql.append(String.format("(%d, '%s')",
                    professionList.get(i).getFachrichtungs_id(), professionList.get(i).getName()));
            if (i < professionList.size() - 1) {
                formattedDataForSql.append(", ");
            }
        }

        insertTestData("fachrichtung", "fachrichtungs_id, name", formattedDataForSql.toString());
    }

    public static void generateDataStringForTableStation(List<Station> stationList) {
        StringBuilder formattedDataForSql = new StringBuilder();

        for (int i = 0; i < stationList.size(); i++) {
            formattedDataForSql.append(String.format("(%d, '%s', %d, %d, %d)",
                    stationList.get(i).getStationId(), stationList.get(i).getName(), stationList.get(i).getAnzahlFreieBetten(),
                    stationList.get(i).getAnzahlBelegteBetten(), stationList.get(i).getKrankenhausId()));
            if (i < stationList.size() - 1) {
                formattedDataForSql.append(", ");
            }
        }

        insertTestData("station", "stations_id, name, anzahlFreieBetten, anzahlBelegteBetten, krankenhaus_id",
                formattedDataForSql.toString());
    }

    public static void generateDataStringForTableFachrichtungStation(List<FachrichtungsStation> fachrichtungsStationList) {
        StringBuilder formattedDataForSql = new StringBuilder();

        for (int i = 0; i < fachrichtungsStationList.size(); i++) {
            formattedDataForSql.append(String.format("(%d, %d)",
                    fachrichtungsStationList.get(i).getFachrichtungsId(), fachrichtungsStationList.get(i).getStationsId()));
            if (i < fachrichtungsStationList.size() - 1) {
                formattedDataForSql.append(", ");
            }
        }

        insertTestData("fachrichtungStation", "fachrichtungs_id, stations_id", formattedDataForSql.toString());
    }

    private static void insertTestData(String tablename, String columns, String values) {
        String sqlInsertQuery = String.format("INSERT INTO %s (%s)%n VALUES %s%n;",
                //Variablen für SQL-Query
                tablename, columns, values);

        System.out.println(sqlInsertQuery);

        try {
            Statement statement = _connection.createStatement();
            ResultSet rs = statement.executeQuery(sqlInsertQuery);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private static String readStringFrom(final String name) {
        return new BufferedReader(new InputStreamReader(Objects.requireNonNull(Thread.currentThread()
                .getContextClassLoader().getResourceAsStream(name)))).lines().collect(Collectors.joining("\n"));
    }
}
