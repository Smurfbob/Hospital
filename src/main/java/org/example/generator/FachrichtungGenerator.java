package org.example.generator;

import com.github.javafaker.Faker;
import org.example.model.Fachrichtung;
import org.example.utils.DatabaseUtils;

import java.util.List;

public class FachrichtungGenerator {

    private static int ID_COUNTER = 0;

    public static List<Fachrichtung> getAmountOfRandomFachrichtung(final int amount) {
        return DatabaseUtils.genAmountOf(amount, () -> {
           final Faker faker = new Faker();
           return new Fachrichtung(++ID_COUNTER, faker.name().title());
        });
    }

}
