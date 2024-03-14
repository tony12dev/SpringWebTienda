/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.java.dao;

import com.mx.java.domain.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author tonny
 */
public interface EmpleadoDao extends JpaRepository<Empleado, Long> {

    Empleado findByNombre(String nombre);
}
