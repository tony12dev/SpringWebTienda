/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mx.java.service;

import com.mx.java.domain.Categoria;
import java.util.List;

/**
 *
 * @author tonny
 */
public interface CategoriaService {
    public List<Categoria> ListarCategoria(Categoria categoria);
    public void Guardar(Categoria categoria);
    public void Eliminar(Categoria categoria);
}
