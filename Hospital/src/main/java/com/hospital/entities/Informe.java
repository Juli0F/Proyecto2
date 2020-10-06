package com.hospital.entities;

import java.io.Serializable;
import java.time.LocalTime;

/**
 *
 * @author Julio
 */
public class Informe implements Serializable {

    private String idInforme;
    private String descripcion;
    private java.sql.Date fecha;
    private int Medico_colegiado;
    private boolean estado;
    private java.sql.Time hora;
    private String codigoCita;

    public Informe(String idInforme, String descripcion, java.sql.Date fechaHora, int Medico_colegiado, boolean estado, java.sql.Time hora,String consutalCodigo) {

        this.idInforme = idInforme;
        this.descripcion = descripcion;
        this.fecha = fechaHora;
        this.Medico_colegiado = Medico_colegiado;
        this.estado = estado;
        this.hora = hora;
        this.codigoCita = consutalCodigo;
    }

    public Informe() {
    
    }

    public String getCodigoCita() {
        return codigoCita;
    }

    public void setCodigoCita(String codigoCita) {
        this.codigoCita = codigoCita;
    }

    
    public String getCodigo() {
        return idInforme;
    }

    public void setCodigo(String codigo) {
        this.idInforme = codigo;
    }

    public java.sql.Time getHora() {
        return hora;
    }

    public void setHora(java.sql.Time hora) {
        this.hora = hora;
    }
    

    
    public String getIdInforme() {
        return this.idInforme;
    }

    public void setIdInforme(String idInforme) {
        this.idInforme = idInforme;
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
