package com.hospital.entities;

import java.io.Serializable;

/**
 *
 * @author Julio
 */
public class Consulta implements Serializable {

    private int idConsulta;
    private String tipo;
    private String costo;
    private String estado;

    public Consulta(int idConsulta, String tipo, String costo, String estado) {

        this.idConsulta = idConsulta;
        this.tipo = tipo;
        this.costo = costo;
        this.estado = estado;
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

    public String getCosto() {
        return this.costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
