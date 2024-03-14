/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.java.service;

import com.mx.java.dao.ProvedorDao;
import com.mx.java.domain.Provedor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tonny
 */
@Service
public class ProvedorServiceImp implements ProvedorService{

    @Autowired
    private ProvedorDao provedorDao;

    @Override
    @Transactional(readOnly = true)
    public List<Provedor> listarProvedores() {
     return (List<Provedor>) provedorDao.findAll();
    }

    @Override
    @Transactional
    public void Guardar(Provedor provedor) {
        provedorDao.save(provedor);
    }

    @Override
    @Transactional
    public void Eliminar(Provedor provedor) {
       provedorDao.delete(provedor);
    }

    @Override
    @Transactional(readOnly = true)
    public Provedor BuscarProvedor(Provedor provedor) {
       return provedorDao.findById(provedor.getIdProvedor()).orElse(null);
    }   
}
