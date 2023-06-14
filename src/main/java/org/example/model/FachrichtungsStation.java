package org.example.model;

public class FachrichtungsStation {

    private int fachrichtungsId;
    private int stationsId;


    public FachrichtungsStation () {

    }

    @Override
    public String toString() {
        return "StationProfession{" +
                "professionID=" + fachrichtungsId +
                ", stationID=" + stationsId +
                '}';
    }

    public FachrichtungsStation (int professionID, int stationID) {
        this.fachrichtungsId = professionID;
        this.stationsId = stationID;
    }

    public int getFachrichtungsId () {
        return fachrichtungsId;
    }

    public FachrichtungsStation setFachrichtungsId (int fachrichtungsId) {
        this.fachrichtungsId = fachrichtungsId;
        return this;
    }

    public int getStationsId () {
        return stationsId;
    }

    public FachrichtungsStation setStationsId (int stationsId) {
        this.stationsId = stationsId;
        return this;
    }
}
