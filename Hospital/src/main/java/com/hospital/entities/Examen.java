package com.hospital.entities;

import java.io.Serializable;

/**
 *
 * @author Julio
 */
public class Examen implements Serializable {

    private String Codigo;
    private String nombre;
    private boolean orden;
    private String descripcion;
    private double costo;
    private String formato;
    private String estado;

    public Examen(String Codigo, String nombre, boolean orden, String descripcion, double costo, String formato, String estado) {

        this.Codigo = Codigo;
        this.nombre = nombre;
        this.orden = orden;
        this.descripcion = descripcion;
        this.costo = costo;
        this.formato = formato;
        this.estado = estado;
    }

    public Examen() {

    }

    public String getCodigo() {
        return this.Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isOrden() {
        return this.orden;
    }

    public void setOrden(boolean orden) {
        this.orden = orden;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCosto() {
        return this.costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getFormato() {
        return this.formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
