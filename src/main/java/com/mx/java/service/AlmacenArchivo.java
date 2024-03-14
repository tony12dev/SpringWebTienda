/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.java.service;

import java.nio.file.Path;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author tonny
 */
public interface AlmacenArchivo {
    
    public void iniciaralmacenDeArchivo();
    public String guardarArchivo(MultipartFile archivo);
    public Path cargarArchivo(String Archivo);
    public Resource cargarComoRecurso(String archivo);
    public void eliminarArchivo(String Archivo);
    
}
