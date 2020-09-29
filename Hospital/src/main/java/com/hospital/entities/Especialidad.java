package com.hospital.entities;

import java.io.Serializable;
/**
 *
 * @author Julio
 */
public class Especialidad implements Serializable{
  private int idEspecialidad;
  private String nombre;
  private boolean estado;
  private int Medico_colegiado;

  public Especialidad (int idEspecialidad, String nombre, boolean estado, int Medico_colegiado ){

  this.idEspecialidad = idEspecialidad;
  this.nombre = nombre;
  this.estado = estado;
  this.Medico_colegiado = Medico_colegiado;
  }

  public int getIdEspecialidad(){
      return this.idEspecialidad;
  }

  public void setIdEspecialidad(int idEspecialidad){
      this.idEspecialidad = idEspecialidad;
  }

  public String getNombre(){
      return this.nombre;
  }

  public void setNombre(String nombre){
      this.nombre = nombre;
  }

  public boolean isEstado(){
      return this.estado;
  }

  public void setEstado(boolean estado){
      this.estado = estado;
  }

  public int getMedico_colegiado(){
      return this.Medico_colegiado;
  }

  public void setMedico_colegiado(int Medico_colegiado){
      this.Medico_colegiado = Medico_colegiado;
  }

}
