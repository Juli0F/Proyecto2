
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
public class ResultadoPaciente {
    
    private String idExamenPaciente;
    private String examen;
    private String codigoPaciente;
    private String nombre;
    private String genero;
    private String fecha;
    private String formato;

    public ResultadoPaciente(String idExamenPaciente, String examen, String codigoPaciente, String nombre, String genero, String fecha, String formato) {
        this.idExamenPaciente = idExamenPaciente;
        this.examen = examen;
        this.codigoPaciente = codigoPaciente;
        this.nombre = nombre;
        this.genero = genero;
        this.fecha = fecha;
        this.formato = formato;
    }

    public String getIdExamenPaciente() {
        return idExamenPaciente;
    }

    public void setIdExamenPaciente(String idExamenPaciente) {
        this.idExamenPaciente = idExamenPaciente;
    }

    public String getExamen() {
        return examen;
    }

    public void setExamen(String examen) {
        this.examen = examen;
    }

    public String getCodigoPaciente() {
        return codigoPaciente;
    }

    public void setCodigoPaciente(String codigoPaciente) {
        this.codigoPaciente = codigoPaciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }
    
}
