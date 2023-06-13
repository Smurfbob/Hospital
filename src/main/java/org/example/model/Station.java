package org.example.model;

public class Station {

    private int stationID;
    private int availableBeds;
    private int unavailableBeds;
    private int kkhID;

    public Station() {

    }

    public Station(int stationID, int availableBeds, int unavailableBeds, int kkhID) {
        this.stationID = stationID;
        this.availableBeds = availableBeds;
        this.unavailableBeds = unavailableBeds;
        this.kkhID = kkhID;
    }

    public int getStationID() {
        return stationID;
    }

    public Station setStationID(int stationID) {
        this.stationID = stationID;
        return this;
    }

    public int getAvailableBeds() {
        return availableBeds;
    }

    public Station setAvailableBeds(int availableBeds) {
        this.availableBeds = availableBeds;
        return this;
    }

    public int getUnavailableBeds() {
        return unavailableBeds;
    }

    public Station setUnavailableBeds(int unavailableBeds) {
        this.unavailableBeds = unavailableBeds;
        return this;
    }

    public int getKkhID() {
        return kkhID;
    }

    public Station setKkhID(int kkhID) {
        this.kkhID = kkhID;
        return this;
    }
}
