package org.example.model;

public class Profession {

    private int ID;
    private String name;

    public Profession() {

    }

    @Override
    public String toString() {
        return "Profession{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                '}';
    }

    public Profession(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public Profession setID(int ID) {
        this.ID = ID;
        return this;
    }

    public String getName() {
        return name;
    }

    public Profession setName(String name) {
        this.name = name;
        return this;
    }
}
