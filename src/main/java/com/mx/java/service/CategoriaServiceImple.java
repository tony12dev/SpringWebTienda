/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.java.service;

import com.mx.java.dao.CategoriaDao;
import com.mx.java.domain.Categoria;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tonny
 */
@Service
public class CategoriaServiceImple implements CategoriaService{

    @Autowired
    private CategoriaDao categoriaDao;
    
    @Transactional
    @Override
     public void Guardar(Categoria categoria) {
     categoriaDao.save(categoria);
     }

    @Transactional
    @Override
    public void Eliminar(Categoria categoria) {
     categoriaDao.save(categoria);
    }    

    @Override
    public List<Categoria> ListarCategoria(Categoria categoria) {
     return (List<Categoria>) categoriaDao.findAll();
    }
}
