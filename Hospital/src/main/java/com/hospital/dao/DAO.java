/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.dao;


import java.util.List;

/**
 *
 * @author Julio
 */
public interface DAO<T,K> {
    
    public boolean insert(T object) ;
    public boolean modify(T object);
    public boolean delete(T object);
    public T obtener(K id);
    public List<T> obtenerTodo();
    public K lastInsertId();
}