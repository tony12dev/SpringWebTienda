/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.java.service;

import com.mx.java.domain.Producto;
import java.util.List;

/**
 *
 * @author tonny
 */
public interface ProductoService {
     public List<Producto> listarProductos();
     public void Guardar(Producto producto);
     public void Eliminar(Producto producto);
     public Producto BuscarProducto(Producto producto);
    
    
}
