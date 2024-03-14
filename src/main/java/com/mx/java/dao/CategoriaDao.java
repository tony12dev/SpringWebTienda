/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mx.java.dao;

import com.mx.java.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author tonny
 */
public interface CategoriaDao extends JpaRepository<Categoria, Long>{
   
}
