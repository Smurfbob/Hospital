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
    public void oneSecond () {
        this.testForDuration(Duration.ofSeconds(1));
    }
    @Test
    public void tenSeconds () {
        this.testForDuration(Duration.ofSeconds(10));
    }

    @Test
    public void thirtySeconds () {
        this.testForDuration(Duration.ofSeconds(30));
    }
    @Test
    public void oneMinute () {
        this.testForDuration(Duration.ofMinutes(1));
    }

    @Test
    public void twoMinutes() {
        this.testForDuration(Duration.ofMinutes(2));
    }

    @Test
    public void fiveMinutes() {
        this.testForDuration(Duration.ofMinutes(5));
    }

    public void testForDuration (final Duration duration) {
        final TestLogger logger = new TestLogger(String.format("Run insert for %d seconds", duration.getSeconds()));
        long plzCounter = 0;
        Instant start = Instant.now();
        logger.addLog(String.format("Start to run for %d seconds", duration.toMillis()));
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
