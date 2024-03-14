/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.java.service;

import com.mx.java.dao.ProductoDao;
import com.mx.java.domain.Producto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.TransactionScoped;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tonny
 */
@Service
public class ProductoServiceImp implements ProductoService{

    @Autowired
    private ProductoDao productoDao;
    
    @PersistenceContext
    EntityManager entityManajer;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> listarProductos() {
      return (List<Producto>) productoDao.findAll();
    }
    
    @Override
    @Transactional
    public void Guardar(Producto producto) {
     productoDao.save(producto);
    }
  

    @Override
    @Transactional
    public void Eliminar(Producto producto) {
     productoDao.delete(producto);
    }

    @Override
    @Transactional
    public Producto BuscarProducto(Producto producto) {
      return productoDao.findById(producto.getIdProducto()).orElse(null);
    }

   
      
}
