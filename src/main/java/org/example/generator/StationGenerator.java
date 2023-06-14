package org.example.generator;

import com.github.javafaker.Faker;
import org.example.model.Krankenhaus;
import org.example.model.Station;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class StationGenerator {

    private static int ID_COUNTER = 0;

    public static int r() {
        return ThreadLocalRandom.current().nextInt(100);
    }

    public static List<Station> getAmountOfRandomStationFachrichtung(final Collection<Krankenhaus> hauser, final int stationAmountForHospital) {
        final Faker faker = new Faker();
        final List<Station> stations = new ArrayList<>();
        for(Krankenhaus krankenhaus : hauser) {
            for(int i=0 ; i < stationAmountForHospital ; i++) {
                final Station station = new Station(++ID_COUNTER, faker.name().fullName(), r(), r(), krankenhaus.getPlz());
                stations.add(station);
            }
        }
        return stations;
    }

}