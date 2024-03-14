/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.java.service;

import com.mx.java.domain.Rol;
import java.util.List;

/**
 *
 * @author tonny
 */
public interface RolService {
    
    public List<Rol> listarRoles();
    public void Guradar(Rol rol);
    public void Eliminar(Rol rol);
            
}
