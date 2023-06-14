package org.example.model;

public class Krankenhaus {
    private long krankenHausId;

    private String name;

    private String strasse;

    private String ansprechpartner;

    private long plz;


    public long getKrankenHausId() {
        return krankenHausId;
    }

    public void setKrankenHausId(long krankenHausId) {
        this.krankenHausId = krankenHausId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getAnsprechpartner() {
        return ansprechpartner;
    }

    public void setAnsprechpartner(String ansprechpartner) {
        this.ansprechpartner = ansprechpartner;
    }

    public long getPlz() {
        return plz;
    }

    public void setPlz(long plz) {
        this.plz = plz;
    }
}
