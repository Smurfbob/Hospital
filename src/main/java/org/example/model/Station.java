package org.example.model;

public class Station {

    private int stationId;
    private String name;
    private int anzahlFreieBetten;
    private int anzahlBelegteBetten;
    private int krankenhausId;

    public Station() {

    }

    @Override
    public String toString() {
        return "Station{" +
                "stationID=" + stationId +
                ", name=" + name +

                ", availableBeds=" + anzahlFreieBetten +
                ", unavailableBeds=" + anzahlBelegteBetten +
                ", kkhID=" + krankenhausId +
                '}';
    }

    public Station(int stationID, String name, int availableBeds, int unavailableBeds, int kkhID) {
        this.stationId = stationID;
        this.name = name;
        this.anzahlFreieBetten = availableBeds;
        this.anzahlBelegteBetten = unavailableBeds;
        this.krankenhausId = kkhID;
    }

    public int getStationId () {
        return stationId;
    }

    public Station setStationId (int stationId) {
        this.stationId = stationId;
        return this;
    }

    public String getName() { return name; }

    public Station setName(String name) {
        this.name = name;
        return this;
    }

    public int getAnzahlFreieBetten () {
        return anzahlFreieBetten;
    }

    public Station setAnzahlFreieBetten (int anzahlFreieBetten) {
        this.anzahlFreieBetten = anzahlFreieBetten;
        return this;
    }

    public int getAnzahlBelegteBetten () {
        return anzahlBelegteBetten;
    }

    public Station setAnzahlBelegteBetten (int anzahlBelegteBetten) {
        this.anzahlBelegteBetten = anzahlBelegteBetten;
        return this;
    }

    public int getKrankenhausId () {
        return krankenhausId;
    }

    public Station setKrankenhausId (int krankenhausId) {
        this.krankenhausId = krankenhausId;
        return this;
    }
}
