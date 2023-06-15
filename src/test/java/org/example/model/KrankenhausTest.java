package org.example.model;

import org.example.service.ServiceProvider;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class KrankenhausTest {

    @Test
    public void test0() {


        final List<Krankenhaus> krankenhaus = ServiceProvider.HOSPITAL_SERVICE.getAll();
        krankenhaus.forEach(System.out::println);


    }


}