package com.hospital.entities;

import java.io.Serializable;
/**
 *
 * @author Julio
 */
public class ExamenLaboratorista implements Serializable{
  private int idExamenLaboratorista;
  private String Examen_Codigo;
  private int Laboratoristas_registro;
  private boolean estado;

  public ExamenLaboratorista (int idExamenLaboratorista, String Examen_Codigo, int Laboratoristas_registro, boolean estado ){

  this.idExamenLaboratorista = idExamenLaboratorista;
  this.Examen_Codigo = Examen_Codigo;
  this.Laboratoristas_registro = Laboratoristas_registro;
  this.estado = estado;
  }

  public int getIdExamenLaboratorista(){
      return this.idExamenLaboratorista;
  }

  public void setIdExamenLaboratorista(int idExamenLaboratorista){
      this.idExamenLaboratorista = idExamenLaboratorista;
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

  public boolean isEstado(){
      return this.estado;
  }

  public void setEstado(boolean estado){
      this.estado = estado;
  }

}
