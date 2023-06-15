package org.example.model;

import org.example.service.PlaceService;
import org.example.service.ServiceProvider;
import org.junit.Before;
import org.junit.Test;

public class OrtTest {

    private static final int DEFAULT_FILL = 5000;
    private final PlaceService placeService = ServiceProvider.PLACE_SERVICE;

    @Before
    public void init() {
        this.placeService.deleteAll();
    }


    @Test
    public void insertBy1 () {
        this.fillWithData(1);
    }

    @Test
    public void insertBy5 () {
        this.fillWithData(5);
    }

    @Test
    public void insertBy10 () {
        this.fillWithData(5);
    }


    private void fillWithData(int amount) {
        amount *= DEFAULT_FILL;
        for(int i=0 ; i < amount ; i++) {
            this.placeService.addNewPlace(new Ort(i+1, String.format("Name: %d", i+1),
                    String.format("Region: %d", i)));
        }
    }


}