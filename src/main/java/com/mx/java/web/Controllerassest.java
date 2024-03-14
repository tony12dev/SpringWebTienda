/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.java.web;

import com.mx.java.service.AlmacenArchivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author tonny
 */
@RestController
@RequestMapping("/assets")
public class Controllerassest {
 
    @Autowired
    private AlmacenArchivo almacenArchivo;
    
    @GetMapping("/{filename:.+}")
    public Resource ObtenerRecurso(@PathVariable("filename") String file){
     return almacenArchivo.cargarComoRecurso(file);
    }
    
    
   
    
    
}
