package org.example.generator;

import com.github.javafaker.Faker;
import org.example.model.Ort;

import java.util.ArrayList;
import java.util.List;

public class OrtGenerator {
    public static List<Ort> generatedOrtDataList(int amountToGenerateData) {
        Faker faker = new Faker();
        List<Ort> listOfOrt = new ArrayList<>();

        // generate 1 data entity {ort entity}
        for (int i = 1; i <= amountToGenerateData; i++) {
            // creates new hospital each iteration
            Ort ort = new Ort();

            // creates fake data
            int plz = faker.number().numberBetween(44145, 44200);
            String name = faker.address().city();
            String region = faker.address().state();

            // sets created fake data into the new ort instance
            ort.setPlz(plz);
            ort.setName(name);
            ort.setRegion(region);

            // adds a new ort to the list each iteration
            listOfOrt.add(ort);
        }

        return listOfOrt;
    }
}