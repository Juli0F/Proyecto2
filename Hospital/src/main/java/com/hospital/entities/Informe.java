package com.hospital.entities;

import java.io.Serializable;
import java.time.LocalTime;

/**
 *
 * @author Julio
 */
public class Informe implements Serializable {

    private String codigo;
    private String descripcion;
    private java.sql.Date fechaHora;
    private int Medico_colegiado;
    private boolean estado;
    private int Consulta_idConsulta;
    private String Pacientes_codigo;
    private java.sql.Time hora;

    public Informe(String idInforme, String descripcion, java.sql.Date fechaHora, int Medico_colegiado, boolean estado, int Consulta_idConsulta, String Pacientes_codigo, java.sql.Time hora) {

        this.codigo = idInforme;
        this.descripcion = descripcion;
        this.fechaHora = fechaHora;
        this.Medico_colegiado = Medico_colegiado;
        this.estado = estado;
        this.Consulta_idConsulta = Consulta_idConsulta;
        this.Pacientes_codigo = Pacientes_codigo;
        this.hora = hora;
    }

    public Informe() {
    
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public java.sql.Time getHora() {
        return hora;
    }

    public void setHora(java.sql.Time hora) {
        this.hora = hora;
    }
    

    
    public String getIdInforme() {
        return this.codigo;
    }

    public void setIdInforme(String idInforme) {
        this.codigo = idInforme;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public java.sql.Date getFechaHora() {
        return this.fechaHora;
    }

    public void setFechaHora(java.sql.Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getMedico_colegiado() {
        return this.Medico_colegiado;
    }

    public void setMedico_colegiado(int Medico_colegiado) {
        this.Medico_colegiado = Medico_colegiado;
    }

    public boolean isEstado() {
        return this.estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getConsulta_idConsulta() {
        return this.Consulta_idConsulta;
    }

    public void setConsulta_idConsulta(int Consulta_idConsulta) {
        this.Consulta_idConsulta = Consulta_idConsulta;
    }

    public String getPacientes_codigo() {
        return this.Pacientes_codigo;
    }

    public void setPacientes_codigo(String Pacientes_codigo) {
        this.Pacientes_codigo = Pacientes_codigo;
    }

}
