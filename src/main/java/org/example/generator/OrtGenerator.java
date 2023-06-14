package org.example.generator;

import com.github.javafaker.Faker;
import org.example.model.Ort;
import org.example.utils.DatabaseUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class OrtGenerator {
    public static List<Ort> generateData(int amountToGenerateData) {
        Faker faker = new Faker();

        List<Ort> listOfOrt = new ArrayList<>();

        try (Connection connection = DatabaseUtils.requestDatabaseConnection()) {
            // generate 1 data entity {ort entity}
            for (int i = 1; i <= amountToGenerateData; i++) {
                // creates new hospital each iteration
                Ort ort = new Ort();

                // creates fake data
                // Einschränkung der PLZ-Nummern
                int plz = faker.number().numberBetween(40000, 50000);
                String name = removeSymbols(faker.address().city());
                String region = removeSymbols(faker.address().state());

                // sets created fake data into the new ort instance
                if (!listOfOrt.stream().map(Ort::getPlz).filter(currentplz -> currentplz == plz).findFirst().isPresent()) {
                    ort.setPlz(plz);
                    ort.setName(name);
                    ort.setRegion(region);

                    // adds a new ort to the list each iteration
                    listOfOrt.add(ort);
                } else {
                    i--;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfOrt;
    }

    private static String removeSymbols(String input) {
        return input.replaceAll("'", "");
    }

}