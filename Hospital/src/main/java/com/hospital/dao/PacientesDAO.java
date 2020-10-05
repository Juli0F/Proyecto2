package com.hospital.dao;

import com.hospital.entities.Paciente;

/**
 *
 * @author Julio
 */
public interface PacientesDAO extends DAO<Paciente,String> {

    //GET_ADMIN_BY_CODE_AND_PWD
    public Paciente getPacienteByCodeAndPwd(String code, String pwd);

}
