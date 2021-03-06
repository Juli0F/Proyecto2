package com.hospital.dao;

import com.hospital.dto.FechaConMasTrabajo;
import com.hospital.entities.Dia;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 *
 * @author Julio
 */
public interface DiaDAO extends DAO<Dia,Integer> {
    
    
    //private final String SEARCH_COINCIDENCIA_OF_DIA_AND_HOUR = "select * from Dia d where fecha = ? and hora = ? and Agenda_codigo = ?";
    public Dia searchCoincidenceByDateHourAndAgenda(java.sql.Date fecha, java.sql.Time hora,int codigoAgenda);
    
    public Dia searchCoincidenceByDateHourAndColegiado(Date fecha, Time hora, int colegiado);

    public List<FechaConMasTrabajo> getDateWithMostWork();

}
