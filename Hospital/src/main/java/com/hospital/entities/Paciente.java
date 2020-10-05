package com.hospital.entities;

import java.io.Serializable;

/**
 *
 * @author Julio
 */
public class Paciente implements Serializable {

    private String codigo;
    private String genero;
    private java.sql.Date fecha;
    private String peso;
    private boolean estado;
    private String tipo_de_sangre;
    private String Persona_dpi;

    public Paciente() {
    }

    
    public Paciente(String codigo, String genero, java.sql.Date fecha, String peso, boolean estado, String tipo_de_sangre, String Persona_dpi) {

        this.codigo = codigo;
        this.genero = genero;
        this.fecha = fecha;
        this.peso = peso;
        this.estado = estado;
        this.tipo_de_sangre = tipo_de_sangre;
        this.Persona_dpi = Persona_dpi;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String isMasculino() {
        return this.genero;
    }

    public void setMasculino(String masculino) {
        this.genero = masculino;
    }

    public java.sql.Date getFecha() {
        return this.fecha;
    }

    public void setFecha(java.sql.Date fecha) {
        this.fecha = fecha;
    }

    public String getPeso() {
        return this.peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public boolean isEstado() {
        return this.estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getTipo_de_sangre() {
        return this.tipo_de_sangre;
    }

    public void setTipoDeSangre(String tipo_de_sangre) {
        this.tipo_de_sangre = tipo_de_sangre;
    }

    public String getPersona_dpi() {
        return this.Persona_dpi;
    }

    public void setPersonaDpi(String Persona_dpi) {
        this.Persona_dpi = Persona_dpi;
    }

}
