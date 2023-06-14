package org.example.generator;

import org.example.model.Fachrichtung;

import java.util.ArrayList;
import java.util.List;

public class FachrichtungGenerator {

    private static int ID_COUNTER = 0;

//  In der Zwischenzeit haben wir die Generierung von Random-Daten verändert und brauchen diese Methode
//  deshalb im Moment nicht mehr. HE

//    public static List<Fachrichtung> getAmountOfRandomFachrichtung(final int amount) {
//        return DatabaseUtils.genAmountOf(amount, () -> {
//           final Faker faker = new Faker();
//           return new Fachrichtung(++ID_COUNTER, faker.name().title());
//        });
//    }

    public static List<Fachrichtung> create30FachrichtungenListe() {
        List<Fachrichtung> listOfFachrichtung = new ArrayList<>();

        for(int i = 0; i < 30; i++) {
            Fachrichtung fachrichtung = new Fachrichtung();
            fachrichtung.setName("fachrichtung-" + i);
            fachrichtung.setFachrichtungs_id(i);
            listOfFachrichtung.add(fachrichtung);
        }
        return listOfFachrichtung;
    }

}
