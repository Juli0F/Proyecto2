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
public class CitaPac {
    private String codigoCita;
    private String consulta;
    private String codigoPaciente;
    private String paciente;
    private String fecha;
    private String hora;
    private String costo;

    public CitaPac(String codigoCita, String consulta, String codigoPaciente, String paciente, String fecha, String hora, String costo) {
        this.codigoCita = codigoCita;
        this.consulta = consulta;
        this.codigoPaciente = codigoPaciente;
        this.paciente = paciente;
        this.fecha = fecha;
        this.hora = hora;
        this.costo = costo;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }
    
    

    public String getCodigoCita() {
        return codigoCita;
    }

    public void setCodigoCita(String codigoCita) {
        this.codigoCita = codigoCita;
    }

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public String getCodigoPaciente() {
        return codigoPaciente;
    }

    public void setCodigoPaciente(String codigoPaciente) {
        this.codigoPaciente = codigoPaciente;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
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
    
            
    
}
