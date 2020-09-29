package com.hospital.entities;

import java.io.Serializable;
/**
 *
 * @author Julio
 */
public class ExamenPaciente implements Serializable{
  private int idExamenPaciente;
  private int Resultado_resultadoCodigo;
  private String Examen_Codigo;
  private int Laboratoristas_registro;
  private int Pacientes_codigo;
  private int Medico_colegiado;
  private boolean realizado;
  private boolean cancelar;
  private boolean estado;

  public ExamenPaciente (int idExamenPaciente, int Resultado_resultadoCodigo, String Examen_Codigo, int Laboratoristas_registro, int Pacientes_codigo, int Medico_colegiado, boolean realizado, boolean cancelar, boolean estado ){

  this.idExamenPaciente = idExamenPaciente;
  this.Resultado_resultadoCodigo = Resultado_resultadoCodigo;
  this.Examen_Codigo = Examen_Codigo;
  this.Laboratoristas_registro = Laboratoristas_registro;
  this.Pacientes_codigo = Pacientes_codigo;
  this.Medico_colegiado = Medico_colegiado;
  this.realizado = realizado;
  this.cancelar = cancelar;
  this.estado = estado;
  }

  public int getIdExamenPaciente(){
      return this.idExamenPaciente;
  }

  public void setIdExamenPaciente(int idExamenPaciente){
      this.idExamenPaciente = idExamenPaciente;
  }

  public int getResultado_resultadoCodigo(){
      return this.Resultado_resultadoCodigo;
  }

  public void setResultado_resultadoCodigo(int Resultado_resultadoCodigo){
      this.Resultado_resultadoCodigo = Resultado_resultadoCodigo;
  }

  public String getExamen_Codigo(){
      return this.Examen_Codigo;
  }

  public void setExamen_Codigo(String Examen_Codigo){
      this.Examen_Codigo = Examen_Codigo;
  }

  public int getLaboratoristas_registro(){
      return this.Laboratoristas_registro;
  }

  public void setLaboratoristas_registro(int Laboratoristas_registro){
      this.Laboratoristas_registro = Laboratoristas_registro;
  }

  public int getPacientes_codigo(){
      return this.Pacientes_codigo;
  }

  public void setPacientes_codigo(int Pacientes_codigo){
      this.Pacientes_codigo = Pacientes_codigo;
  }

  public int getMedico_colegiado(){
      return this.Medico_colegiado;
  }

  public void setMedico_colegiado(int Medico_colegiado){
      this.Medico_colegiado = Medico_colegiado;
  }

  public boolean isRealizado(){
      return this.realizado;
  }

  public void setRealizado(boolean realizado){
      this.realizado = realizado;
  }

  public boolean isCancelar(){
      return this.cancelar;
  }

  public void setCancelar(boolean cancelar){
      this.cancelar = cancelar;
  }

  public boolean isEstado(){
      return this.estado;
  }

  public void setEstado(boolean estado){
      this.estado = estado;
  }

}