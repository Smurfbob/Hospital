package org.example.model;

import org.example.service.ServiceProvider;
import org.example.template.DataAccess;
import org.junit.Test;

import java.util.List;

public class KrankenhausTest {


    private static int DEFAULT_REQUESTS = 5000;
    private static final int DEFAULT_THREAD_AMOUNT = 10000;
    private static final DataAccess<Krankenhaus> HOSPITAL_SERVICE = ServiceProvider.HOSPITAL_SERVICE;

    private static void runWithAmountOfThreads(final int threadAmount, Runnable runnable) {
        for(int i=0 ; i < threadAmount ; i++) {
            new Thread(runnable).start();
        }
    }

    private static void doAmountOfHospitalRequests(final int requestAmount) {
        for(int i=0 ; i < requestAmount ; i++) {
            HOSPITAL_SERVICE.getAll();
        }
    }

    @Test
    public void test1() {
        doAmountOfHospitalRequests(1 * DEFAULT_REQUESTS);
    }


    @Test
    public void test5() {
        doAmountOfHospitalRequests(5 * DEFAULT_REQUESTS);
    }


    @Test
    public void test10() {
        doAmountOfHospitalRequests(10 * DEFAULT_REQUESTS);
    }


    @Test
    public void test20() {
        doAmountOfHospitalRequests(20 * DEFAULT_REQUESTS);
    }




}