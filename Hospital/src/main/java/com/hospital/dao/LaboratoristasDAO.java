package com.hospital.dao;

import com.hospital.dto.PersonaLabDto;
import com.hospital.entities.Laboratorista;
import java.util.List;

/**
 *
 * @author Julio
 */
public interface LaboratoristasDAO extends DAO<Laboratorista,String> {

    public Laboratorista getLaboratoristaByCodeANdPwd(String codigo, String pwd) ;
    public List<PersonaLabDto> getPersonaLabDtoByCodeExamen(String codeExamen);
    public Laboratorista getLabByCodeUsr(String codeUsr);

}
