package org.example.service;

import org.example.model.Station;
import org.example.template.DataAccess;
import org.example.utils.DatabaseUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StationService implements DataAccess<Station> {

    private static final String TABLE_NAME = "station";
    private final Connection connection;

    public StationService(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Station> getAll() {
        return DatabaseUtils.fetchAllElements(TABLE_NAME, this.connection, resultSet -> {

            try {
                final int stationID = resultSet.getInt("stations_id");
                final String name = resultSet.getString("name");
                final int availableBeds = resultSet.getInt("anzahlFreieBetten");
                final int unavailableBeds = resultSet.getInt("anzahlBelegteBetten");
                final int kkhID = resultSet.getInt("krankenhaus_id");
                return new Station(stationID, name, availableBeds, unavailableBeds, kkhID);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
