package com.hospital.dao;

import com.hospital.entities.Persona;

/**
 *
 * @author Julio
 */
public interface PersonaDAO extends DAO<Persona,String> {

    public Persona getPersonaByCodeANdPwd(String codigo, String pwd);

}
