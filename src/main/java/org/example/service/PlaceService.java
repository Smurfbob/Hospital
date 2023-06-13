package org.example.service;

import org.example.model.Place;
import org.example.template.DataAccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlaceService implements DataAccess<Place> {

    private static final String TABLE_NAME = "ort";
    private final Connection connection;

    public PlaceService(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Place> getAll () {
        final List<Place> places = new ArrayList<>();
        try {
            final Statement statement = this.connection.createStatement();
            statement.execute(String.format("SELECT * FROM %s;", TABLE_NAME));
            final ResultSet set = statement.getResultSet();

//            private long PLZ;
//            private String name;
//            private String region;
            while (set.next()) {
                final String name = set.getString("name");
                final String region = set.getString("region");
                final long plz = set.getLong("plz");
                places.add(new Place(plz, name, region ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return places;
    }
}
