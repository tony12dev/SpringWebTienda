/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.java.web;

import com.mx.java.domain.Cliente;
import com.mx.java.service.ClienteService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;

/**
 *
 * @author tonny
 */
@Controller
@Slf4j
public class ControllerCliente {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/api/cliente")
    public String listar(Cliente cliente, Model model) {
        //List<Cliente> clientes = new ArrayList<>();
        var clientes = clienteService.listarClientes();
        var saludo = "Hola como estas";
        model.addAttribute("mensaje", saludo); //model comparte un Atributo
        model.addAttribute("clientes", clientes);
        return "clientes";
    }
    
    @GetMapping("/api/cliente/producto")
    public String productos(){
    return "redirect:/api/producto";
    }
   

    //este metodo es para la vista 
    @GetMapping("/api/cliente/agregar")
    public String agregar(Cliente cliente) {
        return "modificarCliente";
    }

    @PostMapping("/api/cliente/guardar")
    public String GuardarCliente(Cliente cliente, Errors errores) {
        if (errores.hasErrors()) {
            return "modificarCliente";
        }
        clienteService.Guardar(cliente);
        return "redirect:/api/cliente";
    }

    @GetMapping("/api/cliente/editar/{idCliente}")
    public String Modificar(Cliente cliente, Model model) {
        cliente = clienteService.BuscarCliente(cliente);
        model.addAttribute("cliente",cliente);
        return "modificar";
    }

    @GetMapping("/api/cliente/eliminar")
    public String EliminarCliente(Cliente cliente) {
        clienteService.Eliminar(cliente);
        return "redirect:/api/cliente";
    }

}
