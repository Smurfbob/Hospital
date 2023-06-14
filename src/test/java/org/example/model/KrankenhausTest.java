package org.example.model;

import org.example.service.ServiceProvider;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class KrankenhausTest {


    private static final int DEFAULT_INTERVAL = 5000;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy:MM:DD-hh:mm:ss");

    private void request(final int amount, final String name) {

        final LocalDateTime start = LocalDateTime.now();

        for(int i=0 ; i < amount ; i++) {
            ServiceProvider.HOSPITAL_SERVICE.getAll();
        }
        final LocalDateTime endTime = LocalDateTime.now();

        final Duration duration = Duration.between(start, endTime);

        final String response = String.format("%s * %d %s %s --- %s Time: %d", name, DEFAULT_INTERVAL ,KrankenhausTest.class.getSimpleName(),
                start.format(FORMATTER),
                endTime.format(FORMATTER),
                duration.getSeconds());
        System.out.println(response);
    }

    @Test
    public void test0() {
        final int amount = 1;
        request(DEFAULT_INTERVAL * amount, String.format("Test %d", amount));
    }

    @Test
    public void test1() {
        final int amount = 5;
        request(DEFAULT_INTERVAL * amount, String.format("Test %d", amount));
    }

    @Test
    public void test2() {
        final int amount = 10;
        request(DEFAULT_INTERVAL * amount, String.format("Test %d", amount));
    }

    @Test
    public void test3() {
        final int amount = 20;
        request(DEFAULT_INTERVAL * amount, String.format("Test %d", amount));
    }

    @Test
    public void test4() {
        final int amount = 40;
        request(DEFAULT_INTERVAL * amount, String.format("Test %d", amount));
    }

    @Test
    public void test5() {
        final int amount = 80;
        request(DEFAULT_INTERVAL * amount, String.format("Test %d", amount));
    }

    @Test
    public void test6() {
        final int amount = 160;
        request(DEFAULT_INTERVAL * amount, String.format("Test %d", amount));
    }

  
}