package com.hospital.dao;

import com.hospital.entities.Administrador;

/**
 *
 * @author Julio
 */
public interface AdministradorDAO extends DAO<Administrador,Integer> {

//GET_ADMIN_BY_CODE_AND_PSW
    public Administrador getAdminByCodeAndPsw(String codigo, String pwd);
}
