/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.dto;

/**
 *
 * @author julio
 */
public class ExamenIntervalo {
 
    private String codigoExamen;
    private String tipo;
    private String registro;
    private String lab;
    private String fecha;

    public ExamenIntervalo(String codigoExamen, String tipo, String registro, String lab, String fecha) {
        this.codigoExamen = codigoExamen;
        this.tipo = tipo;
        this.registro = registro;
        this.lab = lab;
        this.fecha = fecha;
    }

    public String getCodigoExamen() {
        return codigoExamen;
    }

    public void setCodigoExamen(String codigoExamen) {
        this.codigoExamen = codigoExamen;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getLab() {
        return lab;
    }

    public void setLab(String lab) {
        this.lab = lab;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
}
