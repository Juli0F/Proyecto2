/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.dto;

import java.util.Objects;

/**
 *clase donde se almacenara informacion 
 * con respecto al informe que pertenece al codigo de consulta 
 * asociado
 * @author julio
 */
public class InformeConsulta {
    private String codigoConsulta;
    private String tipo;
    private String costo;
    private String informe;
    private String fecha;
    private String codigoCita;
    private String nombre;
    private String colegiado;

    public InformeConsulta(String codigoCita,String codigoConsulta, String tipo, String costo, String informe, String fecha , String colegiado, String nombre) {
        this.colegiado = colegiado;
        this.nombre = nombre;
        this.codigoCita = codigoCita;
        this.codigoConsulta = codigoConsulta;
        this.tipo = tipo;
        this.costo = costo;
        this.informe = informe;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColegiado() {
        return colegiado;
    }

    public void setColegiado(String colegiado) {
        this.colegiado = colegiado;
    }

    
    public String getCodigoCita() {
        return codigoCita;
    }

    public void setCodigoCita(String codigoCita) {
        this.codigoCita = codigoCita;
    }

    
    public String getCodigoConsulta() {
        return codigoConsulta;
    }

    public void setCodigoConsulta(String codigoConsulta) {
        this.codigoConsulta = codigoConsulta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getInforme() {
        return informe;
    }

    public void setInforme(String informe) {
        this.informe = informe;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.codigoCita);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final InformeConsulta other = (InformeConsulta) obj;
        if (!Objects.equals(this.codigoCita, other.codigoCita)) {
            return false;
        }
        return true;
    }
    
    

  
    
}
