package org.example;

import org.example.model.*;

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

    public static void generateDataStringForTableOrt(List<Ort> placeList) {
        String formattedDataForSql = "";

        for (int i = 0; i < placeList.size(); i++) {
            formattedDataForSql += String.format("(%d, \"%s\", \"%s\")",
                    placeList.get(i).getPlz(), placeList.get(i).getName(), placeList.get(i).getRegion());
            if (i < placeList.size()-1) {
                formattedDataForSql += ", ";
            }
        }

        insertTestData("ort", "plz, name, region", formattedDataForSql);
    }

    public static void generateDataStringForTableKrankenhaus(List<Krankenhaus> hospitalList) {
        String formattedDataForSql = "";

        for (int i = 0; i < hospitalList.size(); i++) {
            formattedDataForSql += String.format("(%d, \"%s\", \"%s\", \"%s\", %d)",
                    hospitalList.get(i).getKrankehausId(), hospitalList.get(i).getName(), hospitalList.get(i).getStrasse(),
                    hospitalList.get(i).getAnsprechpartner(), hospitalList.get(i).getPlz());
            if (i < hospitalList.size()-1) {
                formattedDataForSql += ", ";
            }
        }

        insertTestData("hospital", "krankenhaus_id, name, strasse, ansprechpartner, plz", formattedDataForSql);
    }

    public static void generateDataStringForTableFachrichtung(List<Fachrichtung> professionList) {
        String formattedDataForSql = "";

        for (int i = 0; i < professionList.size(); i++) {
            formattedDataForSql += String.format("(%d, \"%s\")",
                    professionList.get(i).getFachrichtungs_id(), professionList.get(i).getName());
            if (i < professionList.size()-1) {
                formattedDataForSql += ", ";
            }
        }

        insertTestData("fachrichtung", "fachrichtungs_id, name", formattedDataForSql);
    }

    public static void generateDataStringForTableStation(List<Station> stationList) {
        String formattedDataForSql = "";

        for (int i = 0; i < stationList.size(); i++) {
            formattedDataForSql += String.format("(%d, \"%s\", %d, %d, %d)",
                    stationList.get(i).getStationId(), stationList.get(i).getName(), stationList.get(i).getAnzahlFreieBetten(),
                    stationList.get(i).getAnzahlBelegteBetten(), stationList.get(i).getKrankenhausId());
            if (i < stationList.size()-1) {
                formattedDataForSql += ", ";
            }
        }

        insertTestData("station", "stations_id, name, anzahlFreieBetten, anzahlBelegteBetten, krankenhaus_id",
                formattedDataForSql);
    }

    public static void generateDataStringForTableFachrichtungStation(List<FachrichtungsStation> fachrichtungsStationList) {
        String formattedDataForSql = "";

        for (int i = 0; i < fachrichtungsStationList.size(); i++) {
            formattedDataForSql += String.format("(%d, %d)",
                    fachrichtungsStationList.get(i).getFachrichtungsId(), fachrichtungsStationList.get(i).getStationsId());
            if (i < fachrichtungsStationList.size()-1) {
                formattedDataForSql += ", ";
            }
        }

        insertTestData("fachrichtungStation", "fachrichtungs_id, stations_id", formattedDataForSql);
    }

    private static void insertTestData(String tablename, String columns, String values) {
        String sqlQuery = String.format("INSERT INTO %s (%s)%n VALUES %s%n;",
                //Variablen für SQL-Query
                tablename, columns, values);

        System.out.println(sqlQuery);

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
