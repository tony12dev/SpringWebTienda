/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.java.service;

import com.mx.java.dao.RolDao;
import com.mx.java.domain.Rol;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tonny
 */
@Service
public class RolServiceImp implements RolService {

    @Autowired
    private RolDao rolDao;

    @Override
    @Transactional
    public List<Rol> listarRoles() {
      return (List<Rol>) rolDao.findAll();
    }

    @Override
    @Transactional
    public void Guradar(Rol rol) {
      rolDao.save(rol);
    }

    @Override
    public void Eliminar(Rol rol) {
        rolDao.delete(rol);
    }
}
