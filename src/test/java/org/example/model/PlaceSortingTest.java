package org.example.model;

import org.example.service.PlaceService;
import org.example.service.ServiceProvider;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PlaceSortingTest {

    private static final long TEST_ROWS = 5000;

    private final PlaceService placeService = ServiceProvider.PLACE_SERVICE;


    @Before
    public void init() {
        this.placeService.deleteAll();
        for(int i=0 ; i < TEST_ROWS ; i++) {
            final String name = String.format("Test: %d", i+1);
            final String region = String.format("Test: %d", i+1);
            this.placeService.addNewPlace(new Ort(i+1,name, region));
        }
    }


    @Test
    public void sortForOneSecond() {
        this.testForSorting(Duration.ofSeconds(1));
    }


    @Test
    public void sortForTenSeconds() {
        this.testForSorting(Duration.ofSeconds(10));
    }


    @Test
    public void sortForThirtySeconds() {
        this.testForSorting(Duration.ofSeconds(40));
    }


    @Test
    public void sortForOneMinute() {
        this.testForSorting(Duration.ofMinutes(1));
    }


    @Test
    public void sortForTwoMinute() {
        this.testForSorting(Duration.ofMinutes(2));
    }


    @Test
    public void sortForFiveMinute() {
        this.testForSorting(Duration.ofMinutes(5));
    }

    private void testForSorting (final Duration duration) {
        final TestLogger logger = new TestLogger(String.format("Run sorting for %d seconds", duration.getSeconds()));
        Instant start = Instant.now();
        logger.addLog(String.format("Start to sort for %d seconds", duration.getSeconds()));
        List<Ort> list = Collections.emptyList();
        while (Duration.between(start, Instant.now()).toMillis() <= duration.toMillis()) {
            try {
                list = this.placeService.getSortedData("DESC");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        logger.addLog(String.format("Sorted %d places", this.placeService.getOrtAmount()));
        logger.addLog(String.format("\nPlaces\n%s", list.stream().map(Object::toString).collect(Collectors.joining("\n"))));
        logger.writeToFile();
    }


}
