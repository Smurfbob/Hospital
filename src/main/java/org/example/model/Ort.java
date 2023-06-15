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

    public void setPlz (int plz) {
        this.plz = plz;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getRegion () {
        return region;
    }

    public void setRegion (String region) {
        this.region = region;
    }
}
