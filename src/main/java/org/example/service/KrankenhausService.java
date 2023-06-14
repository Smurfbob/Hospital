package org.example.service;

import org.example.model.Krankenhaus;
import org.example.template.DataAccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class KrankenhausService {

    private Connection connection;

    public KrankenhausService(Connection connection) {
        this.connection = connection;
    }


    public List<Krankenhaus> getAllKrankenhausEntries() {
        try {
            Statement statement = this.connection.createStatement();
            ResultSet databaseResult = statement.executeQuery("SELECT * FROM krankenhaus;");

            List<Krankenhaus> listOfKrankenhaus = getKrankenhausList(databaseResult);

            return listOfKrankenhaus;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Krankenhaus> getKrankenhausList(ResultSet databaseResult) throws SQLException {
        List<Krankenhaus> listOfKrankenhaus = new ArrayList<>();

        while (databaseResult.next()) {
            Krankenhaus krankenhaus = new Krankenhaus();

            krankenhaus.setKrankenHausId(databaseResult.getInt("krankenhaus_Id"));
            krankenhaus.setName(databaseResult.getString(2));
            krankenhaus.setStrasse(databaseResult.getString(3));
            krankenhaus.setAnsprechpartner(databaseResult.getString(4));
            krankenhaus.setPlz(databaseResult.getInt("plz"));
            listOfKrankenhaus.add(krankenhaus);

            System.out.println(databaseResult.getInt("krankenhaus_Id"));
            System.out.println(databaseResult.getString(2));
            System.out.println(databaseResult.getString(3));
            System.out.println(databaseResult.getString(4));
            System.out.println(databaseResult.getInt("plz"));
        }

        databaseResult.close();
        return listOfKrankenhaus;
    }
}
