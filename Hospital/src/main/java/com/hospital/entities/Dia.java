package com.hospital.entities;

import java.io.Serializable;
import java.sql.Time;

/**
 *
 * @author Julio
 */
public class Dia implements Serializable {

    private int idDia;
    private java.sql.Date fecha;
    private String descripcion;
    private int Agenda_codigo;
    private String citaCodigo;
    private java.sql.Time hora;

    public Dia(int idDia, java.sql.Date fecha, String descripcion, int Agenda_codigo, String Cita_codigo, java.sql.Time hora) {

        this.hora = hora;
        this.idDia = idDia;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.Agenda_codigo = Agenda_codigo;
        this.citaCodigo = Cita_codigo;
    }
      public Dia(int idDia, String fecha, String descripcion, int Agenda_codigo, String Cita_codigo, String hora) {

        this.hora = java.sql.Time.valueOf(hora);
        this.idDia = idDia;
        this.fecha = java.sql.Date.valueOf(fecha);
        this.descripcion = descripcion;
        this.Agenda_codigo = Agenda_codigo;
        this.citaCodigo = Cita_codigo;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public int getIdDia() {
        return this.idDia;
    }

    public void setIdDia(int idDia) {
        this.idDia = idDia;
    }

    public java.sql.Date getFecha() {
        return this.fecha;
    }

    public void setFecha(java.sql.Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getAgenda_codigo() {
        return this.Agenda_codigo;
    }

    public void setAgenda_codigo(int Agenda_codigo) {
        this.Agenda_codigo = Agenda_codigo;
    }

    public String getCita_codigo() {
        return this.citaCodigo;
    }

    public void setCitaCodigo(String citaCodigo) {
        this.citaCodigo = citaCodigo;
    }

}
