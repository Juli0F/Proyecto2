package com.hospital.dao;

import com.hospital.dto.ResultadoPaciente;
import com.hospital.entities.Resultado;
import java.util.List;

/**
 *
 * @author Julio
 */
public interface ResultadoDAO extends DAO<Resultado,String> {

    
    public List<ResultadoPaciente> resultadoDeHoy(String registro);

}
