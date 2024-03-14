/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.java.web;

import com.mx.java.domain.Categoria;
import com.mx.java.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author tonny
 */
@RequestMapping("/api/category")
@RestController
public class ControllerCategoria {

    @Autowired //Inyeccion de dependencias
    private CategoriaService categoriaService;

    @GetMapping("/agregarCategoria")
    public String Agregar() {
        return "/agregarCategoria";
    }

    @PostMapping("/Guardar")
    public ModelAndView Guardar(Categoria categoria, Model model) {
        categoriaService.Guardar(categoria);
        return new ModelAndView("redirect:/api/producto");
    }
    
    @GetMapping("/api/categoria/eliminar")
    public String Eliminar(Categoria categoria){
    return "redirect:/api/producto";
    }

}
