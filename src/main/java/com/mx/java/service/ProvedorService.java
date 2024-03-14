/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.java.service;

import com.mx.java.domain.Provedor;
import java.util.List;

/**
 *
 * @author tonny
 */
public interface ProvedorService {
    
     public List<Provedor> listarProvedores();
     public void Guardar(Provedor provedor);
     public void Eliminar(Provedor provedor);
     public Provedor BuscarProvedor(Provedor provedor);
}
