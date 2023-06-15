package org.example.generator;

import org.example.model.Fachrichtung;
import org.example.model.FachrichtungsStation;
import org.example.model.Station;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FachrichtungStationGenerator {

    private static final int ID_COUNTER = 0;

    private static <T>  T getRandom(final Collection<T> list) {
        return new ArrayList<>(list).get(ThreadLocalRandom.current().nextInt(list.size()));
    }

    public static List<FachrichtungsStation> getAmountOfRandomStationFachrichtung(final Collection<Fachrichtung> fachrichtungen, final Collection<Station> stations) {
        final List<FachrichtungsStation> fachrichtungsStations = new ArrayList<>();
        for(Station station : stations) {
            final Fachrichtung fachrichtung = getRandom(fachrichtungen);
            fachrichtungsStations.add(new FachrichtungsStation(fachrichtung.getFachrichtungs_id(), station.getStationId()));
        }
        return fachrichtungsStations;
    }
}
