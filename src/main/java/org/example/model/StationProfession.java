package org.example.model;

public class StationProfession {

    private int professionID;
    private int stationID;

    public StationProfession() {

    }

    public StationProfession(int professionID, int stationID) {
        this.professionID = professionID;
        this.stationID = stationID;
    }

    public int getProfessionID() {
        return professionID;
    }

    public StationProfession setProfessionID(int professionID) {
        this.professionID = professionID;
        return this;
    }

    public int getStationID() {
        return stationID;
    }

    public StationProfession setStationID(int stationID) {
        this.stationID = stationID;
        return this;
    }
}
