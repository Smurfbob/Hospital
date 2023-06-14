package org.example.model;

import org.example.service.ServiceProvider;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;
public class KrankenhausTest {

    private void request(final int amount) {
        final LocalDateTime now = LocalDateTime.now();
        for(int i=0 ; i < amount ; i++) {
            ServiceProvider.HOSPITAL_SERVICE.getAll();
        }
        final LocalDateTime endTime = LocalDateTime.now();

        // final String response = String.format("%s --- %s Time: %d", now, endTime, );
    }


    @Test
    public void test1() {

    }

  
}