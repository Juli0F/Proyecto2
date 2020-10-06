package com.hospital.dao;

import com.hospital.entities.DiaTrabajo;

/**
 *
 * @author Julio
 */
public interface DiaTrabajoDAO extends DAO<DiaTrabajo,Integer> {

    public String getDiasDeTrabajo(String registro);

}
