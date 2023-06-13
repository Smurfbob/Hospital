package org.example.service;

import org.example.model.Profession;
import org.example.template.DataAccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class ProfessionService implements DataAccess<Profession> {

    private static final String TABLE_NAME = "fachrichtung";
    private final Connection connection;

    public ProfessionService(final Connection connection) {
        this.connection = Objects.requireNonNull(connection);
    }

    @Override
    public List<Profession> getAll() {
        return DatabaseUtils.fetchAllElements(TABLE_NAME, this.connection, resultSet -> {
            try {
                return new Profession(resultSet.getInt("fachrichtungs_id"), resultSet.getString("name"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
