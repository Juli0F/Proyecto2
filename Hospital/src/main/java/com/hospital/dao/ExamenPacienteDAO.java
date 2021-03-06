package com.hospital.dao;

import com.hospital.dto.ExamenHoy;
import com.hospital.dto.ExamenResultado;
import com.hospital.dto.ResultadoPaciente;
import com.hospital.entities.ExamenPaciente;
import java.util.List;

/**
 *
 * @author Julio
 */
public interface ExamenPacienteDAO extends DAO<ExamenPaciente,Integer> {

    
    public List<ResultadoPaciente> getResultadoPaciente(String registroLaboratorista);
    public List<ExamenResultado> getExamenResultado(String codigoUsuario);
    
    public List<ExamenHoy> getExamenesParaHoy(String labRegistro);
}
