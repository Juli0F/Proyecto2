package com.hospital.entities;

import java.io.Serializable;
/**
 *
 * @author Julio
 */
public class Medico implements Serializable{
  private int colegiado;
  private String inicio;
  private boolean estado;
  private String Persona_dpi;

  public Medico (int colegiado, String inicio, boolean estado, String Persona_dpi ){

  this.colegiado = colegiado;
  this.inicio = inicio;
  this.estado = estado;
  this.Persona_dpi = Persona_dpi;
  }

    public Medico() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  public int getColegiado(){
      return this.colegiado;
  }

  public void setColegiado(int colegiado){
      this.colegiado = colegiado;
  }

  public String getInicio(){
      return this.inicio;
  }

  public void setInicio(String inicio){
      this.inicio = inicio;
  }

  public boolean isEstado(){
      return this.estado;
  }

  public void setEstado(boolean estado){
      this.estado = estado;
  }

  public String getPersona_dpi(){
      return this.Persona_dpi;
  }

  public void setPersona_dpi(String Persona_dpi){
      this.Persona_dpi = Persona_dpi;
  }

}
