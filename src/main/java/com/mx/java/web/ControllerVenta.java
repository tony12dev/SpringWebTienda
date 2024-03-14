/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.java.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mx.java.dao.*;
import com.mx.java.domain.*;
import com.mx.java.service.ProductoService;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.jmx.support.ObjectNameManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author tonny
 */
@Controller
public class ControllerVenta {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProductoDao productoDao;

    @Autowired
    private ProvedorDao provedorDao;


    NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(Locale.US);
   static ArrayList<Producto> productos = new ArrayList();

    @GetMapping("/api/venta")
    public String nuevaVenta(Model model) {
        var listaProductos = productoService.listarProductos();
        model.addAttribute("productos", productos);
        model.addAttribute("listaproductos", listaProductos);
        return "venta/NuevaVenta";
    }

    @GetMapping("/api/venta/producto")
    public String BuscarProducto(@RequestParam(name = "nombre", required = false) String producto,
            Model model) {
        var productoId = productoDao.findByNombre(producto); //Busqueda del producto
        if (productoId == null) {
            model.addAttribute("error", "No se pudo encontrar el producto!!");
            model.addAttribute("productos", productos);
            System.out.println("Nose pudo encontrar nads");

        }
        model.addAttribute("producto", productoId);
        model.addAttribute("productos", productos);

        return "venta/NuevaVenta";
    }

    @GetMapping("/api/venta/id") //Metodo get para el ingreso
    public String enviarProcducto(@RequestParam(name = "cantidad", required = false) Integer cantidad,
            Producto producto, Model model, RedirectAttributes atribute) {
        var productoVender = productoService.BuscarProducto(producto);
        if (cantidad <= 0) { //comparamos si cantidad es mayor a cero 
            atribute.addFlashAttribute("numero", "!No se Aceptan Numeros Negativos,Ni Numeros 0");

        } else if (!consultaStock(producto, cantidad, atribute)) {
            if (!buscarVenta(producto, cantidad)) {

                productoVender.setExistencia(cantidad);
                productos.add(productoVender);
            }
        }

        model.addAttribute("productos", productos);
        model.addAttribute("producto", productoVender);
        System.out.println("productos: " + productos.size());
        return "redirect:/api/venta";
    }

    public Boolean buscarVenta(Producto producto, Integer cantidad) {
        for (Producto p : productos) {
            if (p.getIdProducto().equals(producto.getIdProducto())) {
                var nuevaCantidad = p.getExistencia() + cantidad;
                p.setExistencia(nuevaCantidad);
                return true;
            }
        }
        return false;
    }

    public Boolean consultaStock(Producto producto, Integer cantidad, RedirectAttributes atribute) {
        Producto productoStock = productoService.BuscarProducto(producto);
        var stock = productoStock.getExistencia();
        for (Producto p : productos) {

            if (p.getIdProducto().equals(producto.getIdProducto())) {
                stock = stock - p.getExistencia();
            }
        }
        if (stock < cantidad) {
            atribute.addFlashAttribute("stock", "!No tienes los suficientes productos!!.");
            atribute.addFlashAttribute("existencia", stock);
            atribute.addFlashAttribute("nombre", productoStock.getNombre());
            return true;
        }
        return false;
    }

    @GetMapping("/api/venta/ListaProductos")
    public ModelAndView ListarProductos(@PageableDefault(sort = "nombre", size = 12) Pageable pageable) {
        Page<Producto> productos = productoDao.findAll(pageable);
        return new ModelAndView("venta/ListaProductos").addObject("productos", productos);
    }

    @GetMapping("/api/venta/listaProvedores")
    public ModelAndView listarProvedores(@PageableDefault(sort = "nombre", size = 12) Pageable pageable) {
        Page<Provedor> provedores = provedorDao.findAll(pageable);
        return new ModelAndView("venta/ListaProvedores").addObject("provedores", provedores);
    }

    //Atualizar el stock despues de realizar la venta
    @GetMapping("/limpiarLista")
    public List<Producto> CancelarVenta() {
        productos.clear();
        return productos;
    }

    @DeleteMapping("/api/tablaVenta/eliminar")
    public void EliminarElementoVenta(@RequestBody Producto producto) {
        for (Producto p : productos) {
            if (producto.getIdProducto().equals(p.getIdProducto())) {
                productos.remove(p);
                break;
            }
        }
    }

    @PostMapping("/api/tablaVenta/actualizar")
    public int ActualizarElementoVenta(@RequestBody Producto producto, RedirectAttributes atribute, Model model) {
        Producto productoA = productoService.BuscarProducto(producto);
        if (producto.getExistencia() <= 0) {

            atribute.addFlashAttribute("consulta", "Error en la entrada");
            System.out.println("el producto es : " + producto.getIdProducto());

        } else if (producto.getExistencia() > productoA.getExistencia()) {
            System.out.println("eL prroducto no sirve");
            atribute.addFlashAttribute("stock", "!No tienes los suficientes productos!!.");

        } else {

            for (int contador = 0; contador < productos.size(); contador++) {

                if (productos.get(contador).getIdProducto().equals(producto.getIdProducto())) {
                    productos.get(contador).setExistencia(producto.getExistencia());
                    break;
                }
            }

        }
        return productoA.getExistencia();
    }

    

}
