package org.example;

import com.github.javafaker.Faker;
import org.example.utils.DatabaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RandomDataGenerator {
    public static void main(String[] args) {
        Faker faker = new Faker();
        try (Connection connection = DatabaseUtils.requestDatabaseConnection()) {

            // Retrieve existing postal codes from the "plz" table
            List<Integer> postalCodes = getExistingPostalCodes(connection);

            String insertQuery = "INSERT INTO krankenhaus (krankenhaus_id, name, strasse, ansprechpartner, plz) " +
                    "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(insertQuery);

            for (int i = 1; i <= 8; i++) {
                int hospitalId = faker.number().numberBetween(1, 10000);
                String name = faker.name().fullName();
                String street = faker.address().streetAddress();
                String contactPerson = faker.name().fullName();
                int postalCode = getRandomPostalCode(postalCodes);

                statement.setInt(1, hospitalId);
                statement.setString(2, name);
                statement.setString(3, street);
                statement.setString(4, contactPerson);
                statement.setInt(5, postalCode);

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static List<Integer> getExistingPostalCodes(Connection connection) throws SQLException {
        List<Integer> postalCodes = new ArrayList<>();
        String query = "SELECT plz FROM ort";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int postalCode = resultSet.getInt("plz");
            postalCodes.add(postalCode);
        }
        return postalCodes;
    }

    private static int getRandomPostalCode(List<Integer> postalCodes) {
        int randomIndex = (int) (Math.random() * postalCodes.size());
        return postalCodes.get(randomIndex);
    }
}