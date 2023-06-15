package org.example.model;

import org.example.service.PlaceService;
import org.example.service.ServiceProvider;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;

public class InfiniteAdd {


    private PlaceService placeService = ServiceProvider.PLACE_SERVICE;

    @Before
    public void init() {
        this.placeService.deleteAll();
    }

    @Test
    public void forTimeAmount() {
        final int minutes = 1;
        final TestLogger logger = new TestLogger(String.format("Run for %d minutes", minutes));
        long plzCounter = 0;
        Instant start = Instant.now();
        Duration duration = Duration.ofMinutes(1);
        logger.addLog(String.format("Start to run for %d minutes", minutes));
        while (Duration.between(start, Instant.now()).toMillis() <= duration.toMillis()) {
            plzCounter++;
            final String name = String.format("Name: %d", plzCounter);
            final String region = String.format("Region: %d", plzCounter);
            this.placeService.addNewPlace(new Ort(plzCounter, name, region));
        }
        logger.addLog(String.format("Created %d places", this.placeService.getOrtAmount()));
        logger.writeToFile();
    }

}
