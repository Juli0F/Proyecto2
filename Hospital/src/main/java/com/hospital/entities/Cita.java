package com.hospital.entities;

import java.io.Serializable;

/**
 *
 * @author Julio
 */
public class Cita implements Serializable {

    private String codigo;
    private String descripcion;
    private String estado;
    private String Pacientes_codigo;

    public Cita(String codigo, String descripcion, String estado, String Pacientes_codigo) {

        this.codigo = codigo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.Pacientes_codigo = Pacientes_codigo;
    }

    public Cita() {

    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
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

    public String getPacientes_codigo() {
        return this.Pacientes_codigo;
    }

    public void setPacientes_codigo(String Pacientes_codigo) {
        this.Pacientes_codigo = Pacientes_codigo;
    }

}
