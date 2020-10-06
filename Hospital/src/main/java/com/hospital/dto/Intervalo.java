/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.dto;

/**
 * clase que tiene la funcion de dto, que servira para alamacenar los datos que
 * se mostraran en el reporte de
 * <b><Strong>citas en un intervalo de Tiempo</Strong></b>
 * datos que se mostraran -> nombre de variable
 *
 * codigo de la cita -> codigoCita codigo del paciente-> codigoPaciente nombre
 * del paciente -> nombre fecha en la que eesta programada la cita -> fecha tipo
 * de cita (medica | laboratorio) -> tipo
 *
 * @author julio
 */
public class Intervalo {

    private String codigoCita;
    private String codigoPaciente;
    private String nombre;
    private String fecha;
    private String tipo;

    /**
     * <h3>Constructor</h3>
     * datos que requiere para ser inicializado en orden codigoCita,
     * codigoPaciente, nombre fecha y tipo
     *
     * @param codigoCita
     * @param codigoPaciente
     * @param nombre
     * @param fecha
     * @param tipo
     */
    public Intervalo(String codigoCita, String codigoPaciente, String nombre, String fecha, String tipo) {
        this.codigoCita = codigoCita;
        this.codigoPaciente = codigoPaciente;
        this.nombre = nombre;
        this.fecha = fecha;
        this.tipo = tipo;
    }

    /**
     *obtiene el codigo de la cita
     * @return
     */
    public String getCodigoCita() {
        return codigoCita;
    }

    public void setCodigoCita(String codigoCita) {
        this.codigoCita = codigoCita;
    }

    public String getCodigoPaciente() {
        return codigoPaciente;
    }

    public void setCodigoPaciente(String codigoPaciente) {
        this.codigoPaciente = codigoPaciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
