package com.example.demo.api.model;

/**
 * Implementacion del POJO de la entidad de {@link com.example.demo.api.model.Dueno}.
 *
 * @author  garellano
 * @version 1.0-SNAPSHOT
 * @since   1.0-SNAPSHOT
 */
public class Dueno {
    private int id;
    private String nombre;

    public Dueno() {
    }

    /**
     * Constructor basado en todos los atributos de la clase.
     *
     * @param id a {@link java.lang.Integer} object.
     * @param nombre a {@link java.lang.String} object.
     */
    public Dueno(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * <p>Getter for the field <code>id</code>.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public int getId() {
        return this.id;
    }

    /**
     * <p>Setter for the field <code>id</code>.</p>
     *
     * @param id a int.
     */
    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



}
