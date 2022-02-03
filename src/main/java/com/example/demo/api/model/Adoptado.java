package com.example.demo.api.model;

public class Adoptado {
    private int id;
    private String nombre;
    private String pertenece;
    private String url;

    public Adoptado() {
    }

    public Adoptado(int id, String nombre, String pertenece, String url) {
        this.id = id;
        this.nombre = nombre;
        this.pertenece = pertenece;
        this.url = url;
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

    public String getPertenece() {
        return this.pertenece;
    }

    public void setPertenece(String pertenece) {
        this.pertenece = pertenece;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}

    
