package com.hospital.dao;

import com.hospital.entities.Medico;

/**
 *
 * @author Julio
 */
public interface MedicoDAO extends DAO<Medico,Integer> {
    
    //GET_MEDICO_BY_CODIGO_USUARIO
    public Medico getMedicoByCodigoUsuario(String codigoUsuario);


}
