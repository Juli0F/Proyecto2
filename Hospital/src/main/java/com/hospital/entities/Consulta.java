package com.hospital.entities;

import java.io.Serializable;

/**
 *
 * @author Julio
 */
public class Consulta implements Serializable {

    private int idConsulta;
    private String tipo;
    private double costo;
    private String estado;

    public Consulta(int idConsulta, String tipo, double costo, String estado) {

        this.idConsulta = idConsulta;
        this.tipo = tipo;
        this.costo = costo;
        this.estado = estado;
    }

    public Consulta() {
    
    }

    public int getIdConsulta() {
        return this.idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getCosto() {
        return this.costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
