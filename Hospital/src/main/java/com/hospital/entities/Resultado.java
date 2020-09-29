package com.hospital.entities;

import java.io.Serializable;
/**
 *
 * @author Julio
 */
public class Resultado implements Serializable{
  private int resultadoCodigo;
  private String descripcion;
  private java.sql.Date fechaHora;
  private boolean estado;

  public Resultado (int resultadoCodigo, String descripcion, java.sql.Date fechaHora, boolean estado ){

  this.resultadoCodigo = resultadoCodigo;
  this.descripcion = descripcion;
  this.fechaHora = fechaHora;
  this.estado = estado;
  }

  public int getResultadoCodigo(){
      return this.resultadoCodigo;
  }

  public void setResultadoCodigo(int resultadoCodigo){
      this.resultadoCodigo = resultadoCodigo;
  }

  public String getDescripcion(){
      return this.descripcion;
  }

  public void setDescripcion(String descripcion){
      this.descripcion = descripcion;
  }

  public java.sql.Date getFechaHora(){
      return this.fechaHora;
  }

  public void setFechaHora(java.sql.Date fechaHora){
      this.fechaHora = fechaHora;
  }

  public boolean isEstado(){
      return this.estado;
  }

  public void setEstado(boolean estado){
      this.estado = estado;
  }

}
