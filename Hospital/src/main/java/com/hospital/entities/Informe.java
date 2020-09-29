package com.hospital.entities;

import java.io.Serializable;
/**
 *
 * @author Julio
 */
public class Informe implements Serializable{
  private int idInforme;
  private String descripcion;
  private java.sql.Date fechaHora;
  private int Medico_colegiado;
  private boolean estado;
  private int Consulta_idConsulta;
  private int Pacientes_codigo;

  public Informe (int idInforme, String descripcion, java.sql.Date fechaHora, int Medico_colegiado, boolean estado, int Consulta_idConsulta, int Pacientes_codigo ){

  this.idInforme = idInforme;
  this.descripcion = descripcion;
  this.fechaHora = fechaHora;
  this.Medico_colegiado = Medico_colegiado;
  this.estado = estado;
  this.Consulta_idConsulta = Consulta_idConsulta;
  this.Pacientes_codigo = Pacientes_codigo;
  }

  public int getIdInforme(){
      return this.idInforme;
  }

  public void setIdInforme(int idInforme){
      this.idInforme = idInforme;
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

  public int getMedico_colegiado(){
      return this.Medico_colegiado;
  }

  public void setMedico_colegiado(int Medico_colegiado){
      this.Medico_colegiado = Medico_colegiado;
  }

  public boolean isEstado(){
      return this.estado;
  }

  public void setEstado(boolean estado){
      this.estado = estado;
  }

  public int getConsulta_idConsulta(){
      return this.Consulta_idConsulta;
  }

  public void setConsulta_idConsulta(int Consulta_idConsulta){
      this.Consulta_idConsulta = Consulta_idConsulta;
  }

  public int getPacientes_codigo(){
      return this.Pacientes_codigo;
  }

  public void setPacientes_codigo(int Pacientes_codigo){
      this.Pacientes_codigo = Pacientes_codigo;
  }

}
