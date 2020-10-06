package com.hospital.dao;

import com.hospital.dto.CitaPac;
import com.hospital.dto.InformeConsulta;
import com.hospital.entities.Cita;
import java.util.List;

/**
 *
 * @author Julio
 */
public interface CitaDAO extends DAO<Cita, String> {

    public List<CitaPac> getCitaPac(String codigoAgenda);

    public List<InformeConsulta> getInformeConsulta(String codigoPaciente);
    
    public boolean insertSinConsulta(Cita object);
}
