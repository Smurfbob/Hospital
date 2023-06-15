package org.example.model;

import org.example.service.PlaceService;
import org.example.service.ServiceProvider;
import org.junit.Before;

import java.time.Duration;
import java.time.Instant;

public class InfiniteAdd {


    private PlaceService placeService = ServiceProvider.PLACE_SERVICE;

    @Before
    public void init() {
        this.placeService.deleteAll();
    }

    public void oneSecond () {
        this.testForDuration(Duration.ofSeconds(1));
    }

    public void tenSeconds () {
        this.testForDuration(Duration.ofSeconds(10));
    }


    public void thirtySeconds () {
        this.testForDuration(Duration.ofSeconds(30));
    }

    public void oneMinute () {
        this.testForDuration(Duration.ofMinutes(1));
    }
    public void twoMinutes() {
        this.testForDuration(Duration.ofMinutes(2));
    }

    public void fiveMinutes() {
        this.testForDuration(Duration.ofMinutes(2));
    }

    public void testForDuration (final Duration duration) {
        final int minutes = 1;
        final TestLogger logger = new TestLogger(String.format("Run for %d seconds", duration.getSeconds()));
        long plzCounter = 0;
        Instant start = Instant.now();
        logger.addLog(String.format("Start to run for %d seconds", minutes));
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
