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

}
