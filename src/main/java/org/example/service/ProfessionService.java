package org.example.service;

import org.example.model.Fachrichtung;
import org.example.template.DataAccess;
import org.example.utils.DatabaseUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class ProfessionService implements DataAccess<Fachrichtung> {

    private static final String TABLE_NAME = "fachrichtung";
    private final Connection connection;

    public ProfessionService(final Connection connection) {
        this.connection = Objects.requireNonNull(connection);
    }

    @Override
    public List<Fachrichtung> getAll() {
        return DatabaseUtils.fetchAllElements(TABLE_NAME, this.connection, resultSet -> {
            try {
                return new Fachrichtung(resultSet.getInt("fachrichtungs_id"), resultSet.getString("name"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
