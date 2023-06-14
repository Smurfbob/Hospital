package org.example.model;

public class Fachrichtung {

    private static final String TABLE_NAME = "fachrichtung";

    private int fachrichtungs_id;
    private String name;

    public Fachrichtung () {

    }

    @Override
    public String toString() {
        return "Profession{" +
                "ID=" + fachrichtungs_id +
                ", name='" + name + '\'' +
                '}';
    }

    public Fachrichtung (int ID, String name) {
        this.fachrichtungs_id = ID;
        this.name = name;
    }

    public int getFachrichtungs_id () {
        return fachrichtungs_id;
    }

    public void setFachrichtungs_id (int fachrichtungs_id) {
        this.fachrichtungs_id = fachrichtungs_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
