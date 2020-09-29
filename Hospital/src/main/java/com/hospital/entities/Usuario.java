package com.hospital.entities;

import java.io.Serializable;

/**
 *
 * @author Julio
 */
public class Usuario implements Serializable {

    private String codigo;
    private String clave;
    private boolean estado;
    private String Persona_dpi;

    public Usuario() {
    }
    
    

    public Usuario(String codigo, String clave, boolean estado, String Persona_dpi) {

        this.codigo = codigo;
        this.clave = clave;
        this.estado = estado;
        this.Persona_dpi = Persona_dpi;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getClave() {
        return this.clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean isEstado() {
        return this.estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getPersona_dpi() {
        return this.Persona_dpi;
    }

    public void setPersonaDpi(String Persona_dpi) {
        this.Persona_dpi = Persona_dpi;
    }

}
