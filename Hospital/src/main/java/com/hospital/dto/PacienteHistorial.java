/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.dto;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author julio
 */
public class PacienteHistorial {
   
    private String codigo;
    private String nombrePaciente;
    private String genero;
    private String nacimiento;
    private String peso;
    private String sangre;
    private List<InformeConsulta> consultas;
    private List<ExamenResultado> resultados;

    public PacienteHistorial(String codigo, String nombrePaciente, String genero, String nacimiento, String peso, String sangre) {
        
     
        this.codigo = codigo;
        this.nombrePaciente = nombrePaciente;
        this.genero = genero;
        this.nacimiento = nacimiento;
        this.peso = peso;
        this.sangre = sangre;
    }

    
    
    public String getSangre() {
        return sangre;
    }

    public void setSangre(String sangre) {
        this.sangre = sangre;
    }

    
    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public List<InformeConsulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<InformeConsulta> consultas) {
        this.consultas = consultas;
    }

    public List<ExamenResultado> getResultados() {
        return resultados;
    }

    public void setResultados(List<ExamenResultado> resultados) {
        this.resultados = resultados;
    }

  
    
    
}
