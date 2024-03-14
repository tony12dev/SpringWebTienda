/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.java.service;

import com.mx.java.dao.EmpleadoDao;
import com.mx.java.domain.Empleado;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tonny
 */
@Service
public class EmpleadoServiceImp implements EmpleadoService{

    @Autowired
    private EmpleadoDao empleadoDao;
    
    
    
    @Override
    @Transactional(readOnly = true)
    public List<Empleado> listarEmpleados() {
        return (List<Empleado>) empleadoDao.findAll();
    }

    @Override
    public void Guardar(Empleado empleado) {
        empleadoDao.save(empleado);
    }

    @Override
    public void Eliminar(Empleado empleado) {
        empleadoDao.delete(empleado);
    }

    @Override
    @Transactional
    public Empleado buscarEmpleado(Empleado empleado) {
       return empleadoDao.findById(empleado.getIdEmpleado()).orElse(null);
    }
    
    
}
