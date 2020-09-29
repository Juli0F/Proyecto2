package com.hospital.entities;

import java.io.Serializable;

/**
 *
 * @author Julio
 */
public class Administrador implements Serializable {

    private String codigo;
    private String Persona_dpi;

    public Administrador() {
    }

    
    public Administrador(String codigo, String Persona_dpi) {

        this.codigo = codigo;
        this.Persona_dpi = Persona_dpi;
    }
    

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPersona_dpi() {
        return this.Persona_dpi;
    }

    public void setPersona_dpi(String Persona_dpi) {
        this.Persona_dpi = Persona_dpi;
    }

}
