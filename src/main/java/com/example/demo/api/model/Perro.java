package com.example.demo.api.model;

public class Perro {
    private int id;
    private String nombre;
    private int pertenece;
    private String url;

    public Perro(int id, String nombre, int pertenece, String url) {
        this.id = id;
        this.nombre = nombre;
        this.pertenece = pertenece;
        this.url = url;
    }

    public Perro() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPertenece() {
        return this.pertenece;
    }

    public void setPertenece(int pertenece) {
        this.pertenece = pertenece;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
