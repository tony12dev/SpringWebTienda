/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.java.web;

import com.mx.java.domain.Empleado;
import com.mx.java.domain.Rol;
import com.mx.java.service.EmpleadoService;
import com.mx.java.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author tonny
 */
@Controller
public class ControllerEmpleado {

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private RolService rolService;

    @GetMapping("/api/empleado")
    public String ListaEmpleados(Model model, Rol rol, Empleado empleado) {
        var empleados = empleadoService.listarEmpleados();
        var roles = rolService.listarRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("empleados", empleados);
        return "Empleados/Empleados";
    }

    @PostMapping("/api/empleado/guardar")
    public String GuardarEmpleado(Empleado empleado, RedirectAttributes modelatri) {
        empleadoService.Guardar(empleado);
        modelatri.addFlashAttribute("mensaje", "Registro guardado correctamente");
        return "redirect:/api/empleado";
    }

}
