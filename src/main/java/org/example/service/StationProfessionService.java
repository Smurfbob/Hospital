package org.example.service;

import org.example.model.FachrichtungsStation;
import org.example.template.DataAccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StationProfessionService implements DataAccess<FachrichtungsStation> {


    private static final String TABLE_NAME = "fachrichtungstation";
    private final Connection connection;

    public StationProfessionService(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<FachrichtungsStation> getAll() {
        return DatabaseUtils.fetchAllElements(TABLE_NAME, this.connection, result -> {
            try {
                return new FachrichtungsStation(result.getInt("fachrichtungs_id"), result.getInt("stations_id"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
