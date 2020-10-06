package com.hospital.entities;

import java.io.Serializable;

/**
 *
 * @author Julio
 */
public class ExamenLaboratorista implements Serializable {

    private int idExamenLaboratorista;
    private String Examen_Codigo;
    private String Laboratoristas_registro;
    private boolean estado;
    private boolean cancelar;
    private boolean finalizado;

    public ExamenLaboratorista(int idExamenLaboratorista, String Examen_Codigo, String Laboratoristas_registro, boolean estado) {

        this.idExamenLaboratorista = idExamenLaboratorista;
        this.Examen_Codigo = Examen_Codigo;
        this.Laboratoristas_registro = Laboratoristas_registro;
        this.estado = estado;
        this.cancelar = cancelar;
        this.finalizado = finalizado;
    }

    public boolean isCancelar() {
        return cancelar;
    }

    public void setCancelar(boolean cancelar) {
        this.cancelar = cancelar;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }
    

    public int getIdExamenLaboratorista() {
        return this.idExamenLaboratorista;
    }

    public void setIdExamenLaboratorista(int idExamenLaboratorista) {
        this.idExamenLaboratorista = idExamenLaboratorista;
    }

    public String getExamen_Codigo() {
        return this.Examen_Codigo;
    }

    public void setExamen_Codigo(String Examen_Codigo) {
        this.Examen_Codigo = Examen_Codigo;
    }

    public String getLaboratoristas_registro() {
        return this.Laboratoristas_registro;
    }

    public void setLaboratoristas_registro(String Laboratoristas_registro) {
        this.Laboratoristas_registro = Laboratoristas_registro;
    }

    public boolean isEstado() {
        return this.estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
