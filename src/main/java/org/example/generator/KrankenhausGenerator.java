package org.example.generator;

import com.github.javafaker.Faker;
import org.example.model.Krankenhaus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class KrankenhausGenerator {

    public static List<Krankenhaus> generateKrankenhausList(int amountToGenerateData) {
        Faker faker = new Faker();

        List<Krankenhaus> listOfKrankenhaus = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/hospital", "postgres", "postgres")
        ) {
            // Retrieve existing postal codes from the "plz" table
            List<Integer> postalCodes = getExistingPostalCodes(connection);

            // generate 1 data entity {hospital entity}
            for (int i = 1; i <= amountToGenerateData; i++) {
                // creates new hospital each iteration
                Krankenhaus krankenhaus = new Krankenhaus();

                // creates fake data
                int krankenhaus_id = faker.number().numberBetween(1, 10000);
                String name = faker.company().name();
                String strasse = faker.address().streetAddress();
                String ansprechpartner = faker.name().fullName();
                int postalCode = getRandomPostalCode(postalCodes);

                // sets created fake data into the new hospital instance
                krankenhaus.setKrankehausId(krankenhaus_id);
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

    // ignore this && dont delete this
    private static void test(int amountToGenerateData) {
        Faker faker = new Faker();
        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/hospital", "postgres", "postgres")) {

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
                String name = removeSymbols(faker.company().name());
                String strasse = removeSymbols(faker.address().streetAddress());
                String ansprechpartner = removeSymbols(faker.name().fullName());
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

    private static String removeSymbols(String input) {
        // Regular expression to match symbols
        // Pattern pattern = Pattern.compile("[^a-zA-Z0-9\\s]");

        // Replace symbols with an empty string
        // return pattern.matcher(input).replaceAll("");

        return input.replaceAll("'","");
    }
}