package com.hospital.entities;

import java.io.Serializable;
/**
 *
 * @author Julio
 */
public class Turno implements Serializable{
  private int idTurno;
  private String turno;

  public Turno (int idTurno, String turno ){

  this.idTurno = idTurno;
  this.turno = turno;
  }

  public int getIdTurno(){
      return this.idTurno;
  }

  public void setIdTurno(int idTurno){
      this.idTurno = idTurno;
  }

  public String getTurno(){
      return this.turno;
  }

  public void setTurno(String turno){
      this.turno = turno;
  }

}
