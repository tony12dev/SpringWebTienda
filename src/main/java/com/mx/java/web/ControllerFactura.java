/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.java.web;

import com.mx.java.dao.ClienteDao;
import com.mx.java.domain.Cliente;
import com.mx.java.domain.Producto;
import com.mx.java.service.ClienteService;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author tonny
 */
@Controller
public class ControllerFactura {
    
    @Autowired 
    private ClienteService clienteService;
    
    @Autowired
    private ClienteDao clienteDao;
    
  
 
      ArrayList<Producto> listarproductos = new ArrayList<>();
    
    @GetMapping("/api/venta/factura")
    public ModelAndView vistaFactura(Model model){
        listarproductos = ControllerVenta.productos;
     return new ModelAndView("venta/Factura");
             
    }
    
    
    @GetMapping("/api/factura/cliente")
    public ModelAndView BuscarCliente(Cliente cliente,@RequestParam( name = "idcliente", required = false) 
            Long Idcliente,Model model ){
       cliente = clienteService.FindByIdCliente(Idcliente);
       var clError = "Error el Cliente no existe";
       if(cliente == null){
          
           model.addAttribute("Errorcli", clError);
       }
       
     
        model.addAttribute("cliente", cliente);       
        System.out.println("productos : "+listarproductos);
        return new ModelAndView("venta/Factura");
               
    }
    
    
}
