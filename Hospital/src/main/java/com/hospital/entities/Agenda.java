package com.hospital.entities;

import java.io.Serializable;

/**
 *
 * @author Julio
 */
public class Agenda implements Serializable {

    private int codigo;
    private String estado;
    private int Medico_colegiado;
    private int Laboratoristas_registro;

    public Agenda(int codigo, String estado, int Medico_colegiado, int Laboratoristas_registro) {

        this.codigo = codigo;
        this.estado = estado;
        this.Medico_colegiado = Medico_colegiado;
        this.Laboratoristas_registro = Laboratoristas_registro;
    }

    public Agenda(int Medico_colegiado) {
        this.Medico_colegiado = Medico_colegiado;
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

    public int getMedico_colegiado() {
        return this.Medico_colegiado;
    }

    public void setMedico_colegiado(int Medico_colegiado) {
        this.Medico_colegiado = Medico_colegiado;
    }

    public int getLaboratoristas_registro() {
        return this.Laboratoristas_registro;
    }

    public void setLaboratoristas_registro(int Laboratoristas_registro) {
        this.Laboratoristas_registro = Laboratoristas_registro;
    }

}
