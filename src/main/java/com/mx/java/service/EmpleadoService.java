/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.java.service;

import com.mx.java.domain.Empleado;
import java.util.List;

/**
 *
 * @author tonny
 */
public interface EmpleadoService {
    
    public List<Empleado> listarEmpleados();
    public void Guardar(Empleado empleado);
    public void Eliminar(Empleado empleado);
    public Empleado buscarEmpleado(Empleado empleado);
    
}
