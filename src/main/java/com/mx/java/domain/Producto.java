/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.java.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.springframework.data.repository.NoRepositoryBean;

/**
 *
 * @author tonny
 */
@Data //agregara los setters y getters
@Entity
@Table(name = "producto")
public class Producto implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;
    private String nombre;
    private String contenidoNeto;
    private String fechaCaducidad;
    private String Descripcion;
    private Double precio;    
    private String imagen;
    private Integer existencia;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProvedor")
    private Provedor provedor;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;
   

}
