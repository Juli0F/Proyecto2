package com.hospital.entities;

import java.io.InputStream;
import java.io.Serializable;
import java.sql.Time;

/**
 *
 * @author Julio
 */
public class Resultado implements Serializable {

    private String resultadoCodigo;
    private String descripcion;
    private java.sql.Date fechaHora;
    private boolean estado;
    private String Laboratoristas_registro;
    private int Medico_colegiado;
    private String Pacientes_codigo;
    private String Examen_Codigo;
    private java.sql.Time hora;
    private InputStream inputStreamOrden;
    private byte[] ordenOrden;
    private InputStream inputStreamArchivo;
    private byte[] ordenArchivo;
    

    public Resultado(String resultadoCodigo, String descripcion, java.sql.Date fechaHora, boolean estado, String Laboratoristas_registro, int Medico_colegiado, String Pacientes_codigo, String Examen_Codigo, java.sql.Time hora) {

        this.resultadoCodigo = resultadoCodigo;
        this.descripcion = descripcion;
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.Laboratoristas_registro = Laboratoristas_registro;
        this.Medico_colegiado = Medico_colegiado;
        this.Pacientes_codigo = Pacientes_codigo;
        this.Examen_Codigo = Examen_Codigo;
        this.hora = hora;
    }

    public Resultado(String resultadoCodigo, String descripcion, java.sql.Date fechaHora, boolean estado, String Laboratoristas_registro, int Medico_colegiado, String Pacientes_codigo, String Examen_Codigo, java.sql.Time hora,InputStream inputStream) {
        

        this.resultadoCodigo = resultadoCodigo;
        this.descripcion = descripcion;
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.Laboratoristas_registro = Laboratoristas_registro;
        this.Medico_colegiado = Medico_colegiado;
        this.Pacientes_codigo = Pacientes_codigo;
        this.Examen_Codigo = Examen_Codigo;
        this.hora = hora;
        this.inputStreamOrden = inputStream;
                
    }

    /**
     * para mostrar en el navegador
     * @return 
     */
    public byte[] getOrdenOrden() {
        return ordenOrden;
    }

    /**
     * metodo para mostrar en el navegador la orden
     * @param ordenOrden 
     */
    public void setOrdenOrden(byte[] ordenOrden) {
        this.ordenOrden = ordenOrden;
    }
/**
 * metodo para guardar el archivo en la db
 * @return 
 */
    public InputStream getInputStreamArchivo() {
        return inputStreamArchivo;
    }
/**
 * para guardar en la db
 * @param inputStreamArchivo 
 */
    public void setInputStreamArchivo(InputStream inputStreamArchivo) {
        this.inputStreamArchivo = inputStreamArchivo;
    }
/**
 * para mostrar en el navegador
 * @return 
 */

    public byte[] getOrdenArchivo() {
        return ordenArchivo;
    }
/**
 * settear el archivo que se mostrara en el navegador
 * @param ordenArchivo 
 */
    public void setOrdenArchivo(byte[] ordenArchivo) {
        this.ordenArchivo = ordenArchivo;
    }

    /**
     * para guardar la orden en la db
     * @return 
     */
    public InputStream getInputStreamOrden() {
        return inputStreamOrden;
    }

    /**
     * para settear la orden 
     * @param inputStreamOrden 
     */
    
    public void setInputStreamOrden(InputStream inputStreamOrden) {
        this.inputStreamOrden = inputStreamOrden;
    }
    

    public Resultado() {
        
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }
    

    public String getResultadoCodigo() {
        return this.resultadoCodigo;
    }

    public void setResultadoCodigo(String resultadoCodigo) {
        this.resultadoCodigo = resultadoCodigo;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public java.sql.Date getFechaHora() {
        return this.fechaHora;
    }

    public void setFechaHora(java.sql.Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public boolean isEstado() {
        return this.estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getLaboratoristas_registro() {
        return this.Laboratoristas_registro;
    }

    public void setLaboratoristasRegistro(String Laboratoristas_registro) {
        this.Laboratoristas_registro = Laboratoristas_registro;
    }

    public int getMedico_colegiado() {
        return this.Medico_colegiado;
    }

    public void setMedicoColegiado(int Medico_colegiado) {
        this.Medico_colegiado = Medico_colegiado;
    }

    public String getPacientesCodigo() {
        return this.Pacientes_codigo;
    }

    public void setPacientes_codigo(String Pacientes_codigo) {
        this.Pacientes_codigo = Pacientes_codigo;
    }

    public String getExamenCodigo() {
        return this.Examen_Codigo;
    }

    public void setExamen_Codigo(String Examen_Codigo) {
        this.Examen_Codigo = Examen_Codigo;
    }

}
