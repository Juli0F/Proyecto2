package com.hospital.entities;

import java.io.Serializable;

/**
 *
 * @author Julio
 */
public class DiaTrabajo implements Serializable {

    private int idTrabajoLaboratoristaLaboratorista;
    private int Turno_idTurno;
    private String Laboratoristas_registro;
    private boolean estado;

    public DiaTrabajo(int idTrabajoLaboratoristaLaboratorista, int Turno_idTurno, String Laboratoristas_registro, boolean estado) {

        this.idTrabajoLaboratoristaLaboratorista = idTrabajoLaboratoristaLaboratorista;
        this.Turno_idTurno = Turno_idTurno;
        this.Laboratoristas_registro = Laboratoristas_registro;
        this.estado = estado;
    }

    public int getIdTrabajoLaboratoristaLaboratorista() {
        return this.idTrabajoLaboratoristaLaboratorista;
    }

    public void setIdTrabajoLaboratoristaLaboratorista(int idTrabajoLaboratoristaLaboratorista) {
        this.idTrabajoLaboratoristaLaboratorista = idTrabajoLaboratoristaLaboratorista;
    }

    public int getTurno_idTurno() {
        return this.Turno_idTurno;
    }

    public void setTurno_idTurno(int Turno_idTurno) {
        this.Turno_idTurno = Turno_idTurno;
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
