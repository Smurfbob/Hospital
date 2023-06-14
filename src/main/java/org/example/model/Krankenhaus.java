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

    public int getKrankehausId () {
        return krankehausId;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStrasse () {
        return strasse;
    }

    public void setStrasse (String strasse) {
        this.strasse = strasse;
    }

    public String getAnsprechpartner () {
        return ansprechpartner;
    }

    public void setAnsprechpartner (String ansprechpartner) {
        this.ansprechpartner = ansprechpartner;
    }

    public void setKrankehausId (int krankehausId) {
        this.krankehausId = krankehausId;
    }

    public int getPlz () {
        return plz;
    }

    public void setPlz (int plz) {
        this.plz = plz;
    }
}
