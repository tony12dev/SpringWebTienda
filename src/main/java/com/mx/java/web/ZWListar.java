/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.java.web;

import com.mx.java.domain.Producto;
import com.mx.java.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tonny
 */

@RestController
public class ZWListar {

    @Autowired
private ProductoService productoService;
    
    
    @RequestMapping(value = "/listar")
    public List<Producto> Listas() {     
        return productoService.listarProductos();
    }
    
}
