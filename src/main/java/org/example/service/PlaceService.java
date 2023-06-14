package org.example.service;

import org.example.model.Ort;
import org.example.template.DataAccess;

import java.sql.Connection;
import java.sql.SQLException;
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
                final long plz = set.getLong("plz");
                return new Ort(plz, name, region );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
