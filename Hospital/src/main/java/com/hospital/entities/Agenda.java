package com.hospital.entities;

import java.io.Serializable;

/**
 *
 * @author Julio
 */
public class Agenda implements Serializable {

    private int codigo;
    private String estado;
    private String Medico_colegiado;
    private int noEnviar;
    private String Laboratoristas_registro;

    /**
     * Agenda Medica
     * @param codigo
     * @param estado
     * @param Medico_colegiado 
     */
    public Agenda(int codigo, String estado, String Medico_colegiado ) {
        this.codigo = codigo;
        this.estado = estado;
        this.Medico_colegiado = Medico_colegiado;
    }

    /**
     * Agenda para el laboratorista
     * @param codigo
     * @param estado
     * @param Laboratoristas_registro 
     */
    public Agenda(int codigo, String estado, String Laboratoristas_registro, int extraNoEnviar) {
        this.Medico_colegiado = null;
        this.codigo = codigo;
        this.estado = estado;
        this.Laboratoristas_registro = Laboratoristas_registro;
        this.noEnviar = noEnviar;
    }
    

    
    public Agenda(int codigo, String estado, String Medico_colegiado, String Laboratoristas_registro) {

        this.codigo = codigo;
        this.estado = estado;
        this.Medico_colegiado = Medico_colegiado;
        this.Laboratoristas_registro = Laboratoristas_registro;
    }

    public Agenda() {
        //this.Medico_colegiado = Medico_colegiado;
    }
    

    public int getCodigo() {
        return this.codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMedico_colegiado() {
        return this.Medico_colegiado;
    }

    public void setMedico_colegiado(String Medico_colegiado) {
        this.Medico_colegiado = Medico_colegiado;
    }

    public String getLaboratoristas_registro() {
        return this.Laboratoristas_registro;
    }

    public void setLaboratoristas_registro(String Laboratoristas_registro) {
        this.Laboratoristas_registro = Laboratoristas_registro;
    }

}
