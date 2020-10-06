package com.hospital.dao;

import com.hospital.dto.CantidadInformes;
import com.hospital.dto.CitaPac;
import com.hospital.dto.InformeConsulta;
import com.hospital.dto.Intervalo;
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
    /**
     * datos para llenar el Dto -> Intervalo
     * 
     * @param fechaInicial
     * @param fechaFinal
     * @param colegiado
     * @return 
     */
    public List<Intervalo> getCitasIntervaloFechasByColegiado(String fechaInicial, String fechaFinal, String colegiado);
    public List<CantidadInformes> getCantidadDeInformesPorPaciente(String fechaInicial, String fechaFinal) ;
    public List<Intervalo> getCitasParaHoyFechasByColegiado( String colegiado);
}
