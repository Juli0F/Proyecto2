package com.hospital.entities;

import java.io.Serializable;
/**
 *
 * @author Julio
 */
public class Dia implements Serializable{
  private int idDia;
  private java.sql.Date fecha;
  private String descripcion;
  private int Agenda_codigo;
  private int Cita_codigo;

  public Dia (int idDia, java.sql.Date fecha, String descripcion, int Agenda_codigo, int Cita_codigo ){

  this.idDia = idDia;
  this.fecha = fecha;
  this.descripcion = descripcion;
  this.Agenda_codigo = Agenda_codigo;
  this.Cita_codigo = Cita_codigo;
  }

  public int getIdDia(){
      return this.idDia;
  }

  public void setIdDia(int idDia){
      this.idDia = idDia;
  }

  public java.sql.Date getFecha(){
      return this.fecha;
  }

  public void setFecha(java.sql.Date fecha){
      this.fecha = fecha;
  }

  public String getDescripcion(){
      return this.descripcion;
  }

  public void setDescripcion(String descripcion){
      this.descripcion = descripcion;
  }

  public int getAgenda_codigo(){
      return this.Agenda_codigo;
  }

  public void setAgenda_codigo(int Agenda_codigo){
      this.Agenda_codigo = Agenda_codigo;
  }

  public int getCita_codigo(){
      return this.Cita_codigo;
  }

  public void setCita_codigo(int Cita_codigo){
      this.Cita_codigo = Cita_codigo;
  }

}
