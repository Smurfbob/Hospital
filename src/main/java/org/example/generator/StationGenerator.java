package org.example.generator;

import com.github.javafaker.Faker;
import org.example.model.Krankenhaus;
import org.example.model.Station;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class StationGenerator {

    private static int ID_COUNTER = 0;

    public static int r() {
        return ThreadLocalRandom.current().nextInt(20);
    }

    public static List<Station> getAmountOfRandomStationFachrichtung(final Collection<Krankenhaus> hospitals, final int stationAmountForHospital) {
        final Faker faker = new Faker();
        final List<Station> stations = new ArrayList<>();
        for(Krankenhaus hospital : hospitals) {
            for(int i=0 ; i < stationAmountForHospital ; i++) {
                Station station = new Station(++ID_COUNTER, String.format("Station %d", i), r(), r(), hospital.getKrankehausId());
                stations.add(station);
            }
        }
        return stations;
    }



}