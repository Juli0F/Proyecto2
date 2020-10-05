package com.hospital.dao;

import com.hospital.dto.MedicoDto;
import com.hospital.entities.Medico;
import java.util.List;

/**
 *
 * @author Julio
 */
public interface MedicoDAO extends DAO<Medico,Integer> {
    
    //GET_MEDICO_BY_CODIGO_USUARIO
    public Medico getMedicoByCodigoUsuario(String codigoUsuario);
    //GET_ADMIN_BY_CODE_AND_PWD 
    public Medico getMedicoByCodeANdPwd(String code, String pwd);
    
    //MEDICO_CON_ESPECIALIDAD
    public List<MedicoDto> getMedicoConEspecialida();


}
