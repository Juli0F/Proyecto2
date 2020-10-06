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
public class PersonaLabDto {
    private boolean disponible;
    private String nombre;
    private String dpi;
    private String registro;
    private String examen;
    private String codeExamen;
    private String diasDeTrabajo;

    public PersonaLabDto(boolean disponible, String nombre, String dpi, String registro, String examen, String codeExamen, String diasDeTrabajo) {
        
        this.diasDeTrabajo = diasDeTrabajo;
        this.disponible = disponible;
        this.nombre = nombre;
        this.dpi = dpi;
        this.registro = registro;
        this.examen = examen;
        this.codeExamen = codeExamen;
        
    }

    public String getDiasDeTrabajo() {
        return diasDeTrabajo;
    }

    public void setDiasDeTrabajo(String diasDeTrabajo) {
        this.diasDeTrabajo = diasDeTrabajo;
    }
    

    public String getExamen() {
        return examen;
    }

    public void setExamen(String examen) {
        this.examen = examen;
    }

    public String getCodeExamen() {
        return codeExamen;
    }

    public void setCodeExamen(String codeExamen) {
        this.codeExamen = codeExamen;
    }

    

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }
    
    
}
