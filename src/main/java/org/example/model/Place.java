package org.example.model;

public class Place {
    private long PLZ;
    private String name;
    private String region;

    public Place (long PLZ, String name, String region) {
        this.PLZ = PLZ;
        this.name = name;
        this.region = region;
    }


    @Override
    public String toString () {
        return "Place{" +
                "PLZ=" + PLZ +
                ", name='" + name + '\'' +
                ", region='" + region + '\'' +
                '}';
    }

    public Place() {

    }

    public long getPLZ () {
        return PLZ;
    }

    public Place setPLZ (long PLZ) {
        this.PLZ = PLZ;
        return this;
    }

    public String getName () {
        return name;
    }

    public Place setName (String name) {
        this.name = name;
        return this;
    }

    public String getRegion () {
        return region;
    }

    public Place setRegion (String region) {
        this.region = region;
        return this;
    }
}
