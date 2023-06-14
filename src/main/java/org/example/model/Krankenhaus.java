package org.example.model;

public class Krankenhaus {

    private int krankehausId;
    private String name;
    private String strasse;
    private String ansprechpartner;
    private int plz;

    public Krankenhaus () {

    }

    public Krankenhaus (int kkhID, String name, String street, String contact, int plz) {
        this.krankehausId = kkhID;
        this.name = name;
        this.strasse = street;
        this.ansprechpartner = contact;
        this.plz = plz;
    }


    @Override
    public String toString() {
        return "Hospital{" +
                "kkhID=" + krankehausId +
                ", name='" + name + '\'' +
                ", street='" + strasse + '\'' +
                ", contact='" + ansprechpartner + '\'' +
                ", plz='" + plz + '\'' +
                '}';
    }

    public long getKrankehausId () {
        return krankehausId;
    }



    public String getName() {
        return name;
    }

    public Krankenhaus setName(String name) {
        this.name = name;
        return this;
    }

    public String getStrasse () {
        return strasse;
    }

    public Krankenhaus setStrasse (String strasse) {
        this.strasse = strasse;
        return this;
    }

    public String getAnsprechpartner () {
        return ansprechpartner;
    }

    public Krankenhaus setAnsprechpartner (String ansprechpartner) {
        this.ansprechpartner = ansprechpartner;
        return this;
    }

    public Krankenhaus setKrankehausId (int krankehausId) {
        this.krankehausId = krankehausId;
        return this;
    }

    public int getPlz () {
        return plz;
    }

    public Krankenhaus setPlz (int plz) {
        this.plz = plz;
        return this;
    }
}
