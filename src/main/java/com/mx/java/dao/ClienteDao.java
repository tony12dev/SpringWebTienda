/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.java.dao;

import com.mx.java.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author tonny
 */
public interface ClienteDao extends JpaRepository<Cliente, Long>{
   //conexion con la base de datos   la que nos va permitir administrar Todas las consultas ala base de datos 
  
}
