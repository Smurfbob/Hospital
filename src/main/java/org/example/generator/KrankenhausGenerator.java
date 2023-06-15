package org.example.generator;

import com.github.javafaker.Faker;
import org.example.model.Krankenhaus;
import org.example.utils.DatabaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KrankenhausGenerator {
       public static List<Krankenhaus> generateKrankenhausList(int amountToGenerateData) {

        List<Krankenhaus> listOfKrankenhaus = new ArrayList<>();

        try (Connection connection = DatabaseUtils.requestDatabaseConnection()) {
            // Retrieve existing postal codes from the "plz" table
            List<Integer> postalCodes = getExistingPostalCodes(connection);

            // generate 1 data entity {hospital entity}
            for (int i = 1; i <= amountToGenerateData; i++) {
                // creates new hospital each iteration
                Krankenhaus krankenhaus = new Krankenhaus();

                // creates fake data
                String name = String.format("Krankenhaus %d", i);
                String strasse = String.format("Strasse %d", i);
                String ansprechpartner = String.format("Ansprechpartner %d", i);
                int postalCode = getRandomPostalCode(postalCodes);

                // sets created fake data into the new hospital instance
                krankenhaus.setKrankehausId(i);
                krankenhaus.setName(name);
                krankenhaus.setStrasse(strasse);
                krankenhaus.setAnsprechpartner(ansprechpartner);
                krankenhaus.setPlz(postalCode);

                // adds a new krankenhaus to the list each iteration
                listOfKrankenhaus.add(krankenhaus);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfKrankenhaus;
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

    // ignore this && don't delete this
    private static void test(int amountToGenerateData) {

        Faker faker = new Faker();
        try (Connection connection = DatabaseUtils.requestDatabaseConnection()) {

            // Retrieve existing postal codes from the "plz" table
            List<Integer> postalCodes = getExistingPostalCodes(connection);

            String insertQuery = "INSERT INTO krankenhaus (krankenhaus_id, name, strasse, ansprechpartner, plz) " +
                    "VALUES (?, ?, ?, ?, ?) " +
                    "ON CONFLICT (krankenhaus_id) DO UPDATE " +
                    "SET name = excluded.name, strasse = excluded.strasse, " +
                    "ansprechpartner = excluded.ansprechpartner, plz = excluded.plz";

            PreparedStatement statement = connection.prepareStatement(insertQuery);

            for (int i = 1; i <= amountToGenerateData; i++) {
                // creates fake data
                int krankenhaus_id = faker.number().numberBetween(1, 10000);
                String name = faker.company().name().replaceAll("[-+.^:,']","");
                String strasse = faker.address().streetAddress().replaceAll("[-+.^:,']","");
                String ansprechpartner = faker.name().fullName().replaceAll("[-+.^:,']","");
                int postalCode = getRandomPostalCode(postalCodes);

                statement.setInt(1, krankenhaus_id);
                statement.setString(2, name);
                statement.setString(3, strasse);
                statement.setString(4, ansprechpartner);
                statement.setInt(5, postalCode);

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}