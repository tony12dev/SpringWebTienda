/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mx.java.service;

import com.mx.java.domain.Cliente;
import java.util.List;

/**
 *
 * @author tonny
 */
public interface ClienteService {
     public List<Cliente> listarClientes();
     public void Guardar(Cliente cliente);
     public void Eliminar(Cliente cliente);
     public Cliente BuscarCliente(Cliente cliente);
     public Cliente FindByIdCliente(Long cliente);
}
