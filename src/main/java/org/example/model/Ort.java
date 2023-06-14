package org.example.model;

public class Ort {
    private long plz;
    private String name;
    private String region;

    public Ort (long PLZ, String name, String region) {
        this.plz = PLZ;
        this.name = name;
        this.region = region;
    }


    @Override
    public String toString () {
        return "Place{" +
                "PLZ=" + plz +
                ", name='" + name + '\'' +
                ", region='" + region + '\'' +
                '}';
    }

    public Ort () {

    }

    public long getPlz () {
        return plz;
    }

    public Ort setPlz (long plz) {
        this.plz = plz;
        return this;
    }

    public String getName () {
        return name;
    }

    public Ort setName (String name) {
        this.name = name;
        return this;
    }

    public String getRegion () {
        return region;
    }

    public Ort setRegion (String region) {
        this.region = region;
        return this;
    }
}
