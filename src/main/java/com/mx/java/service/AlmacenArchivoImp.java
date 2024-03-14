/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.java.service;

import jakarta.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author tonny
 */
@Service
public class AlmacenArchivoImp implements AlmacenArchivo{

    @Value("${storage.location}")
    private String storageLocation;
    
    @PostConstruct //cada ves que alla una nueva instancia
    @Override
    public void iniciaralmacenDeArchivo() {
        try {
            Files.createDirectories(Paths.get(storageLocation));
        } catch (Exception e) {
           e.printStackTrace();
            System.out.println("Error al almacenar un archivo");
        }
    }

    @Override
    public String guardarArchivo(MultipartFile archivo) {
        String nombreArchivo = archivo.getOriginalFilename();
        if(archivo.isEmpty()){
            System.out.println("Nose puede almacenar un archivo Vacio");
        }
        try {
            InputStream inputStream = archivo.getInputStream();
            Files.copy(inputStream,Paths.get(storageLocation).resolve(nombreArchivo), StandardCopyOption.REPLACE_EXISTING);
            //Remplasamos un archivo con su mismo nombre
        } catch (Exception e) {
          e.printStackTrace();
        }
        return nombreArchivo;
    }

    @Override
    public Path cargarArchivo(String Archivo) {
     return Paths.get(storageLocation).resolve(Archivo);
    }

    @Override
    public Resource cargarComoRecurso(String nombreArchivo) {
        
        Resource recurso = null;
        try {
            Path archivo = cargarArchivo(nombreArchivo);
             recurso = new UrlResource(archivo.toUri());
            if(recurso.exists() || recurso.isReadable()){
                return recurso;
            }else{
                System.out.println("nose pudo cargar la foto");
            }
            
        } catch (MalformedURLException excepcion) {
           excepcion.printStackTrace();
        }
        return recurso;
    }
 

    @Override
    public void eliminarArchivo(String nombreArchivo) {
    Path archivo = cargarArchivo(nombreArchivo);
        try {
            FileSystemUtils.deleteRecursively(archivo);
        } catch (Exception e) {
         e.printStackTrace();
        }  
  }
}