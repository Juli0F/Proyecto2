package com.hospital.entities;

import java.io.Serializable;

/**
 *
 * @author Julio
 */
public class Cita implements Serializable {

    private int codigo;
    private String descripcion;
    private String estado;
    private int Pacientes_codigo;

    public Cita(int codigo, String descripcion, String estado, int Pacientes_codigo) {

        this.codigo = codigo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.Pacientes_codigo = Pacientes_codigo;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getPacientes_codigo() {
        return this.Pacientes_codigo;
    }

    public void setPacientes_codigo(int Pacientes_codigo) {
        this.Pacientes_codigo = Pacientes_codigo;
    }

}
