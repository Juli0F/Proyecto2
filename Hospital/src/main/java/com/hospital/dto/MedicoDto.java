/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.dto;

import java.util.Objects;

/**
 *
 * @author julio
 */
public class MedicoDto {
    /*
     *<th scope="col">Codigo</th>
              <th scope="col">Medico</th>
              <th scope="col">Colegiado</th>
              <th scope="col">Correo</th>
              <th scope="col">Hora Entrada</th>
              <th scope="col">Hora Salida</th>
              <th scope="col">Hora Salida</th>
              <th scope="col">Especialidades</th>
     * 
     */
    private String codigo;
    private String nombre;
    private String correo;
    private String horaEntrada;
    private String horaSalida;
    private String especialidades;
    private String colegiado;
    private int id;

    public MedicoDto(int id,String codigo, String medico, String correo, String horaEntrada, String horaSalida, String especialidades,String colegiado) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = medico;
        this.correo = correo;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.especialidades = especialidades;
        this.colegiado = colegiado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
    public String getColegiado() {
        return colegiado;
    }

    public void setColegiado(String colegiado) {
        this.colegiado = colegiado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(String especialidades) {
        this.especialidades = especialidades;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.codigo);
        hash = 79 * hash + Objects.hashCode(this.colegiado);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MedicoDto other = (MedicoDto) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (!Objects.equals(this.colegiado, other.colegiado)) {
            return false;
        }
        return true;
    }
    
            
}
