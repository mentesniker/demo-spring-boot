package com.example.demo.api.model;

public class Administrador {
    private String mail;
    private String password;
    private String rol;


    public Administrador(String mail, String password, String rol) {
        this.mail = mail;
        this.password = password;
        this.rol = rol;
    }

    public Administrador() {
    }


    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return this.rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}
