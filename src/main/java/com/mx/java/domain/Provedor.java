/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.java.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author tonny
 */
@Data
@Entity
@Table(name = "provedor")

public class Provedor implements Serializable{

   private static final long serialVersionUID = 1L;    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProvedor;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private String rfc;
    private String observaciones;

}
