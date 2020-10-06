package com.hospital.dao;

import com.hospital.entities.Examen;

/**
 *
 * @author Julio
 */
public interface ExamenDAO extends DAO<Examen,String> {


    public Examen getExamenByName(String name);
}
