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
public class ExamenHoy {
    private String paciente;
    private String codigoPaciente;
    private String examenNombre;

    public ExamenHoy(String paciente, String codigoPaciente, String examenNombre) {
        this.paciente = paciente;
        this.codigoPaciente = codigoPaciente;
        this.examenNombre = examenNombre;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getCodigoPaciente() {
        return codigoPaciente;
    }

    public void setCodigoPaciente(String codigoPaciente) {
        this.codigoPaciente = codigoPaciente;
    }

    public String getExamenNombre() {
        return examenNombre;
    }

    public void setExamenNombre(String examenNombre) {
        this.examenNombre = examenNombre;
    }
    
    
}
