/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.java.web;

import com.mx.java.domain.Provedor;
import com.mx.java.service.ProvedorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tonny
 */
@RestController
@RequestMapping("/provedores")
public class ControllerPrueba {
    
   @Autowired
   private ProvedorService provedorService;
   
   @GetMapping
   public List<Provedor> listar(Provedor provedor){ 
       return provedorService.listarProvedores();
   
   
   }
}
