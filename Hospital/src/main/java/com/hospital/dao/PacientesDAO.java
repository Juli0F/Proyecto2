package com.hospital.dao;

import com.hospital.dto.PacienteHistorial;
import com.hospital.entities.Paciente;
import java.util.List;

/**
 *
 * @author Julio
 */
public interface PacientesDAO extends DAO<Paciente,String> {

    //GET_ADMIN_BY_CODE_AND_PWD
    public Paciente getPacienteByCodeAndPwd(String code, String pwd);

    public List<PacienteHistorial> getPacienteHistoria();
}
