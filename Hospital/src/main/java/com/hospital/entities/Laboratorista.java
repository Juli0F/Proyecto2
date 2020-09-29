package com.hospital.entities;

import java.io.Serializable;
/**
 *
 * @author Julio
 */
public class Laboratorista implements Serializable{
  private int registro;
  private java.sql.Date inicio;
  private String ocupado;
  private String estado;
  private String Persona_dpi;

  public Laboratorista (int registro, java.sql.Date inicio, String ocupado, String estado, String Persona_dpi ){

  this.registro = registro;
  this.inicio = inicio;
  this.ocupado = ocupado;
  this.estado = estado;
  this.Persona_dpi = Persona_dpi;
  }

  public int getRegistro(){
      return this.registro;
  }

  public void setRegistro(int registro){
      this.registro = registro;
  }

  public java.sql.Date getInicio(){
      return this.inicio;
  }

  public void setInicio(java.sql.Date inicio){
      this.inicio = inicio;
  }

  public String getOcupado(){
      return this.ocupado;
  }

  public void setOcupado(String ocupado){
      this.ocupado = ocupado;
  }

  public String getEstado(){
      return this.estado;
  }

  public void setEstado(String estado){
      this.estado = estado;
  }

  public String getPersona_dpi(){
      return this.Persona_dpi;
  }

  public void setPersona_dpi(String Persona_dpi){
      this.Persona_dpi = Persona_dpi;
  }

}
