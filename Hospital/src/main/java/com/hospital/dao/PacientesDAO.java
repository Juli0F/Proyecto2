package com.hospital.dao;

import com.hospital.dto.ExamenIntervalo;
import com.hospital.dto.ExamenMedico;
import com.hospital.dto.PacienteHistorial;
import com.hospital.dto.Ultimos;
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
    
    public List<Ultimos> getLastExamenesByCodePaciente(String codigoPaciente);
    
    public List<ExamenIntervalo> getExamenPorIntervalo(String tipoExamen, String fechaInicial, String fechaFinal,String codigoPaciente);
    
    public List<Ultimos> getLastConsultas(String codigoPaciente);
    
    public List<ExamenMedico> getConsultaByCodigoPacienteMedicoFechas(String codePaciente,
            String medico, String fechaInicial, String fechaFinal);
}
