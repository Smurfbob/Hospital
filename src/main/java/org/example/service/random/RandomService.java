package org.example.service.random;

import java.util.concurrent.ThreadLocalRandom;

public class RandomService {


    public static final String getRandomString() {
        return getRandomString(25, CharGenerator.values());
    }

    public static String getRandomString(final int length, final CharGenerator... generators) {
        final StringBuilder builder = new StringBuilder();
        for(int i=0 ; i < length ; i++) {
            builder.append(generators[ThreadLocalRandom.current().nextInt(generators.length)].getRandomChar());
        }
        return builder.toString();
    }


}
