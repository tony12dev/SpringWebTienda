/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mx.java.service;

import com.mx.java.dao.ClienteDao;
import com.mx.java.domain.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tonny
 */
//Servicio
@Service
public class ClienteServiceImp implements ClienteService {

    //inyeccionde dependencias 
    @Autowired
    private ClienteDao clienteDao;
    
    

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> listarClientes() {
        return (List<Cliente>) clienteDao.findAll();
    }

    @Override
    @Transactional
    public void Guardar(Cliente cliente) {
      clienteDao.save(cliente);
    }

    @Override
    @Transactional
    public void Eliminar(Cliente cliente) {
       clienteDao.delete(cliente);
    }

    @Override
    @Transactional
    public Cliente BuscarCliente(Cliente cliente) {
     return clienteDao.findById(cliente.getIdCliente()).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente FindByIdCliente(Long cliente) {
       return clienteDao.findById(cliente).orElse(null);               
    }
}
