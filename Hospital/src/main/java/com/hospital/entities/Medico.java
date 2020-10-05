package com.hospital.entities;

import java.io.Serializable;
import java.sql.Time;

/**
 *
 * @author Julio
 */
public class Medico implements Serializable {

    private int colegiado;
    private java.sql.Date inicio;
    private boolean estado;
    private String Persona_dpi;
    private java.sql.Time entrada;
    private java.sql.Time salida;

    public Medico(int colegiado, java.sql.Date inicio, boolean estado, String Persona_dpi, java.sql.Time entrada, java.sql.Time salida) {

        this.colegiado = colegiado;
        this.inicio = inicio;
        this.estado = estado;
        this.Persona_dpi = Persona_dpi;
        this.entrada = entrada;
        this.salida = salida;
    }

    public Time getEntrada() {
        return entrada;
    }

    public void setEntrada(Time entrada) {
        this.entrada = entrada;
    }

    public Time getSalida() {
        return salida;
    }

    public void setSalida(Time salida) {
        this.salida = salida;
    }

    
    public Medico() {

    }

    public int getColegiado() {
        return this.colegiado;
    }

    public void setColegiado(int colegiado) {
        this.colegiado = colegiado;
    }

    public java.sql.Date getInicio() {
        return this.inicio;
    }

    public void setInicio(java.sql.Date inicio) {
        this.inicio = inicio;
    }

    public boolean isEstado() {
        return this.estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getPersona_dpi() {
        return this.Persona_dpi;
    }

    public void setPersona_dpi(String Persona_dpi) {
        this.Persona_dpi = Persona_dpi;
    }

}
