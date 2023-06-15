package org.example.service;

import org.example.model.Ort;
import org.example.template.DataAccess;
import org.example.utils.DatabaseUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlaceService implements DataAccess<Ort> {

    private static final String TABLE_NAME = "ort";
    private final Connection connection;

    public PlaceService(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Ort> getAll () {
        return DatabaseUtils.fetchAllElements(TABLE_NAME, connection, set -> {
            try {
                final String name = set.getString("name");
                final String region = set.getString("region");
                final int plz = set.getInt("plz");
                return new Ort(plz, name, region );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void deleteAll() {
        final String sqlStatement = "DELETE FROM ort;";
        try {
            final Statement statement = this.connection.createStatement();
            statement.execute(sqlStatement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void addNewPlace(final Ort ort) {
        final String sqlStatement = String.format("INSERT INTO ort (plz, \"name\", region) VALUES (%d, '%s', '%s');", ort.getPlz(), ort.getName(), ort.getRegion());
        try {
            final Statement statement = this.connection.createStatement();
            statement.execute(sqlStatement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Ort getOrtByPlz(final int plz) {
        try {
            final Statement statement = this.connection.createStatement();
            statement.execute(String.format("SELECT * FROM ort WHERE plz = %d;"), plz);
            final ResultSet resultSet = statement.getResultSet();
            if(resultSet.next())
                return new Ort(plz, resultSet.getString("name"), resultSet.getString("region"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Ort> getHigherPlz(final int plz) {
        return getByPlzDistance(plz, '>');
    }



    public void getSortedData(String ascType) throws SQLException {
        Statement statement = this.connection.createStatement();
        ResultSet databaseResult = statement.executeQuery("SELECT * FROM ort" +
                "ORDER BY plz" + ascType + ";" );

        List<Ort> listOfOrt = new ArrayList<>();
        while (databaseResult.next()){
            listOfOrt.add(
                    new Ort(databaseResult.getInt("plz"),
                    databaseResult.getString(2),
                    databaseResult.getString(3))
            );
        }

        for (Ort ort : listOfOrt) {
            System.out.println(ort.getPlz());
            System.out.println(ort.getName());
            System.out.println(ort.getRegion());
        }

    }


    public List<Ort> getSmallerPlz(final int plz) {
        return getByPlzDistance(plz, '<');
    }

    private List<Ort> getByPlzDistance (final int plz, final char operation) {
        final List<Ort> places = new ArrayList<>();
        try {
            final Statement statement = this.connection.createStatement();
            statement.execute(String.format("SELECT * FROM ort WHERE plz %s %d;",operation, plz));
            final ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                places.add(new Ort(resultSet.getInt("plz"),
                        resultSet.getString("name"), resultSet.getString("region")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return places;
    }

}
