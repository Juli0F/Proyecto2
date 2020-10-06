package com.hospital.dao;

import com.hospital.entities.Agenda;

/**
 *
 * @author Julio
 */
public interface AgendaDAO extends DAO<Agenda, Integer> {

    /**
     * Obtiene la Agenda por el codigo del usuario
     * pasando por Medico, es decir obtiene la agenda 
     * de un medico, mediante su codigo de usuario
     * 
     * @param codigoUsuarioMedico
     * @return 
     */
    public Agenda obtenerAgendaMedica(String codigoUsuarioMedico);

    //GET_AGENDA_BY_COLEGIADO
    /**
     * obtiene la agenda del medico, mediante su numero de colegiado
     * @param colegiadoMedico
     * @return 
     */
    public Agenda getAgendaByColegido(String colegiadoMedico);
    /**
     * obtiene la agendda de un Laboratorista mediante su codigo  registro
     * @param registroLaboratorista
     * @return 
     */
    public Agenda getAgendaByRegistro(String registroLaboratorista);
    
    
    public boolean insertLab(Agenda object);

}
