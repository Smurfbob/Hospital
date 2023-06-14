package org.example.service;

import org.example.model.Hospital;
import org.example.template.DataAccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HospitalService implements DataAccess<Hospital> {

    private Connection connection;

    public HospitalService (Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Hospital> getAll () {
        final List<Hospital> hospitals = new ArrayList<>();
        try {
            Statement statement = this.connection.createStatement();
            statement.execute("SELECT * FROM krankenhaus;");
            ResultSet set = statement.getResultSet();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
