package com.hospital.dao;

import com.hospital.entities.Consulta;
import java.util.List;

/**
 *
 * @author Julio
 */
public interface ConsultaDAO extends DAO<Consulta,Integer> {

    public Consulta getCOnsultaByTipo(String  tipo);
    
    public List<Consulta> getConsultaSearchLike(String parameter);

}
