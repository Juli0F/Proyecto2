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
public class ExamenMedico {
    private String consulta;
    private String fecha;
    private String hora;
    private String medico;

    public ExamenMedico(String consulta, String fecha, String hora, String medico) {
        this.consulta = consulta;
        this.fecha = fecha;
        this.hora = hora;
        this.medico = medico;
    }

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }
    
    
    
}
