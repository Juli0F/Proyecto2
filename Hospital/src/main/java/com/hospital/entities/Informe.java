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
    private java.sql.Date fecha;
    private int Medico_colegiado;
    private boolean estado;
    private java.sql.Time hora;
    private String consultaCodigo;

    public Informe(String idInforme, String descripcion, java.sql.Date fechaHora, int Medico_colegiado, boolean estado, java.sql.Time hora,String consutalCodigo) {

        this.codigo = idInforme;
        this.descripcion = descripcion;
        this.fecha = fechaHora;
        this.Medico_colegiado = Medico_colegiado;
        this.estado = estado;
        this.hora = hora;
        this.consultaCodigo = consutalCodigo;
    }

    public Informe() {
    
    }

    public String getConsultaCodigo() {
        return consultaCodigo;
    }

    public void setConsultaCodigo(String consultaCodigo) {
        this.consultaCodigo = consultaCodigo;
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

    public java.sql.Date getFecha() {
        return this.fecha;
    }

    public void setFecha(java.sql.Date fecha) {
        this.fecha = fecha;
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



}
