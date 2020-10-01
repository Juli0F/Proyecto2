package com.hospital.dao;

import com.hospital.entities.Agenda;

/**
 *
 * @author Julio
 */
public interface AgendaDAO extends DAO<Agenda,Integer> {

    public Agenda obtenerAgendaMedica(String codigoUsuarioMedico) ;

}
