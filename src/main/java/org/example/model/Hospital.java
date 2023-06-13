package org.example.model;

public class Hospital {

    private long kkhID;
    private String name;
    private String street;
    private String contact;
    private String plz;

    public Hospital() {

    }

    public Hospital(long kkhID, String name, String street, String contact, String plz) {
        this.kkhID = kkhID;
        this.name = name;
        this.street = street;
        this.contact = contact;
        this.plz = plz;
    }

    public long getKkhID() {
        return kkhID;
    }

    public Hospital setKkhID(long kkhID) {
        this.kkhID = kkhID;
        return this;
    }

    public String getName() {
        return name;
    }

    public Hospital setName(String name) {
        this.name = name;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public Hospital setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getContact() {
        return contact;
    }

    public Hospital setContact(String contact) {
        this.contact = contact;
        return this;
    }

    public String getPlz() {
        return plz;
    }

    public Hospital setPlz(String plz) {
        this.plz = plz;
        return this;
    }
}
