package org.example.service.random;

import java.util.concurrent.ThreadLocalRandom;

public enum CharGenerator {


    NUMBERS('0', 10),
    NON_CAPITALS('a',  26),
    CAPITALS('A', 26);

    private final char start;
    private final int length;

    CharGenerator (char start, int length) {
        this.start = start;
        this.length = length;
    }

    public static CharGenerator getRandomGenerator() {
        return CharGenerator.values()[ThreadLocalRandom.current().nextInt(CharGenerator.values().length)];
    }

    public char getRandomChar() {
        return (char) (ThreadLocalRandom.current().nextInt(this.length) + this.start);
    }
}
