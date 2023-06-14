package org.example.model;

import org.example.service.ServiceProvider;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class KrankenhausTest {


    private static final int DEFAULT_INTERVAL = 5000;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy:MM:DD-hh:mm:ss");

    private void request(final int amount, final String name) {
        final LocalDateTime now = LocalDateTime.now();
        for(int i=0 ; i < amount ; i++) {
            ServiceProvider.HOSPITAL_SERVICE.getAll();
        }
        final LocalDateTime endTime = LocalDateTime.now();

        final String response = String.format("%s %s %s --- %s Time: ", name ,KrankenhausTest.class.getSimpleName(),
                now.format(FORMATTER),
                endTime.format(FORMATTER) );
        System.out.println(response);
    }




    @Test
    public void test0() {
        request(DEFAULT_INTERVAL * 1, "Test 1");
    }

    @Test
    public void test1() {
        request(DEFAULT_INTERVAL * 5, "Test 5");
    }

    @Test
    public void test2() {
        request( DEFAULT_INTERVAL * 10, "Test 10");
    }

    @Test
    public void test3() {
        request( DEFAULT_INTERVAL * 20, "Test 20");
    }

  
}