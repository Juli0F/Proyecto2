package com.hospital.dao;

import com.hospital.entities.Usuario;

/**
 *
 * @author Julio
 */
public interface UsuarioDAO extends DAO<Usuario,Integer> {
//GET_USR_BY_CODIGO_AND_CLAVE
    public Usuario getUsrByCodigoAndClave(String codigo, String clave);

}
