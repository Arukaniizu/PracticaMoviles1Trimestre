package com.example.EjercicioMoviles1TRIM.Interfaces.domain;

public class Lugar {
    private int id;
    private String title;
    private Ubicacion location;

    public Lugar(){ }

    public Lugar(int id, String title, Ubicacion location) {
        this.id = id;
        this.title = title;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Ubicacion getLocation() {
        return location;
    }

    public void setLocation(Ubicacion location) {
        this.location = location;
    }

}
