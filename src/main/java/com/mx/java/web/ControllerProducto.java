/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.java.web;

import com.mx.java.dao.ProductoDao;
import com.mx.java.domain.*;
import com.mx.java.service.*;
import java.text.DecimalFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.ModelFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author tonny
 */
@Controller
public class ControllerProducto {

    @Autowired
    private ProductoService productoService;
    @Autowired
    private ProvedorService provedorService;
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private AlmacenArchivo almacenArchivo;
    @Autowired
    private ProductoDao productoDao;    
    
    
    @GetMapping("/api/producto")
    public ModelAndView ListarProductos(@PageableDefault(sort = "nombre", size = 12) Pageable pageable
    ,Categoria categoria,Model model,Producto producto) {
       Page<Producto> productos = productoDao.findAll(pageable);        
        var provedores = provedorService.listarProvedores();
        var categorias = categoriaService.ListarCategoria(categoria);
        return new ModelAndView("producto")
                .addObject("productos", productos)
                .addObject("categoria" , categorias)
                .addObject("provedores", provedores);
    }

    @GetMapping("/api/producto/detalle/{idProducto}")
    public String detalleProducto(Producto producto, Model model) {
        producto = productoService.BuscarProducto(producto);
        model.addAttribute("producto", producto);
        return "/productos/detalleProducto";
    }

    @PostMapping("/api/producto/guardar")
    public String GuardarProducto(@Validated Producto producto, Errors errores,
            @RequestParam(name = "file", required = false) MultipartFile imagen, Model model,RedirectAttributes atribute) {
        String rutaArchivo = almacenArchivo.guardarArchivo(imagen);
        producto.setImagen(rutaArchivo);
        productoService.Guardar(producto);
        atribute.addFlashAttribute("mensaje","El producto a sido guardado correctamente");
        return "redirect:/api/producto";
    }
 

    //Actualizacion sin imagen
    @PostMapping("/api/producto/actualizar")
    public String editarProducto(Producto producto) {
        Producto existingProduct = productoService.BuscarProducto(producto);
        producto.setImagen(existingProduct.getImagen());
        productoService.Guardar(producto);
        return "redirect:/api/producto";
    }

    @PostMapping("/updateimage")
    public String updateProducto(@ModelAttribute Producto producto,
            @RequestParam(name = "file", required = false) MultipartFile imagen,
            RedirectAttributes modelatri) {
        Producto updateProducto = productoService.BuscarProducto(producto);
        String rutaArchivo = almacenArchivo.guardarArchivo(imagen);
        updateProducto.setImagen(rutaArchivo);
        productoService.Guardar(updateProducto);
        modelatri.addFlashAttribute("mensaje", "La imagen a sido Cambiada");
        return "redirect:/api/producto/editar/" + updateProducto.getIdProducto();
    }

    @GetMapping("/api/producto/editar/{idProducto}")
    public String editarProducto(Producto producto, Model model,
            Provedor provedor, Categoria categoria) {
        producto = productoService.BuscarProducto(producto);
        var provedores = provedorService.listarProvedores();
        var categorias = categoriaService.ListarCategoria(categoria);
        model.addAttribute("categoria", categorias);
        model.addAttribute("provedores", provedores);
        model.addAttribute("producto", producto);
        return "/productos/modificar";
    }

    //Editar existencia de producto
    @GetMapping("/api/producto/existencia/{idProducto}")
    public String exitenciaProducto(Producto producto, Model model) {
        producto = productoService.BuscarProducto(producto);
        model.addAttribute("producto", producto);
        return "productos/Existencia";
    }
    
    

    //Actualizar Existencia
    @PostMapping("/Actualizar")
    public void UpdateExitencia(@RequestBody Producto producto) {
        Producto productoDt = productoService.BuscarProducto(producto);
        //Existencia Actual + la nueva existencia
        productoDt.setExistencia(producto.getExistencia() + productoDt.getExistencia());
        productoService.Guardar(productoDt);
    }
    

    @DeleteMapping("/api/producto/eliminar")
    public String EliminarProducto(@RequestBody Producto producto) {
        productoService.Eliminar(producto);
        return "redirect:/api/producto";
    }    
}
