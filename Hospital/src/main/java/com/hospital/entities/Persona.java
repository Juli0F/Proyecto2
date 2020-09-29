package com.hospital.entities;

import java.io.Serializable;

/**
 *
 * @author Julio
 */
public class Persona implements Serializable {

    private String dpi;
    private String nombre;
    private String telefono;
    private String correo;
    private boolean estado;

    public Persona() {
    }

    
    public Persona(String dpi, String nombre, String telefono, String correo, boolean estado) {

        this.dpi = dpi;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.estado = estado;
    }

    public String getDpi() {
        return this.dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isEstado() {
        return this.estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
