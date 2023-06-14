package org.example.generator;

import com.github.javafaker.Faker;
import org.example.model.Ort;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrtGenerator {
    public static List<Ort> generateData(int amountToGenerateData) {
        Faker faker = new Faker();

        List<Ort> listOfOrt = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/hospital", "postgres", "postgres")
        ) {
            // generate 1 data entity {ort entity}
            for (int i = 1; i <= amountToGenerateData; i++) {
                // creates new hospital each iteration
                Ort ort = new Ort();

                // creates fake data
                // Einschränkung der PLZ-Nummern
                int plz = (40000+i);
                String name = String.format("Ortsname %d", i);
                String region = String.format("Region %d", i);

                // sets created fake data into the new ort instance
                    ort.setPlz(plz);
                    ort.setName(name);
                    ort.setRegion(region);

                    // adds a new ort to the list each iteration
                    listOfOrt.add(ort);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfOrt;
    }

}