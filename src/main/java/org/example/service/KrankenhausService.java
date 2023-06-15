package org.example.service;

import org.example.model.Krankenhaus;
import org.example.template.DataAccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class KrankenhausService implements DataAccess<Krankenhaus> {

    private final Connection connection;

    public KrankenhausService(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<Krankenhaus> getAll() {
        return this.getAllKrankenhausEntries();
    }

    private List<Krankenhaus> getAllKrankenhausEntries() {
        try {
            Statement statement = this.connection.createStatement();
            ResultSet databaseResult = statement.executeQuery("SELECT * FROM krankenhaus;");
            return getKrankenhausList(databaseResult);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Krankenhaus> getKrankenhausList(ResultSet databaseResult) throws SQLException {
        List<Krankenhaus> listOfKrankenhaus = new ArrayList<>();
        while (databaseResult.next()) {
            Krankenhaus krankenhaus = new Krankenhaus();
            krankenhaus.setKrankehausId(databaseResult.getInt("krankenhaus_Id"));
            krankenhaus.setName(databaseResult.getString(2));
            krankenhaus.setStrasse(databaseResult.getString(3));
            krankenhaus.setAnsprechpartner(databaseResult.getString(4));
            krankenhaus.setPlz(databaseResult.getInt("plz"));
            listOfKrankenhaus.add(krankenhaus);
        }
        databaseResult.close();
        return listOfKrankenhaus;
    }
}
