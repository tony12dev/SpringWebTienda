
package com.mx.java.web;

import com.mx.java.dao.ProvedorDao;
import com.mx.java.domain.Provedor;
import com.mx.java.service.ProvedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author tonny
 */
@Controller
public class ControllerProvedor {
   
    @Autowired
    private ProvedorService provedorService;
    
    @GetMapping("/api/provedor")
    public String ListarProvedor(Provedor provedor, Model model){
     var provedores = provedorService.listarProvedores();
     model.addAttribute("provedores",provedores);
        return "provedor";
    }
    
    @GetMapping("/api/provedor/agregar")
    public String agregar(Provedor provedor){
      return "modificar";
    }
    
    @PostMapping("/api/provedor/guardar")
    public String guardarProvedor(Provedor provedor, Errors errores){
       if(errores.hasErrors()){
         return "modificar";
       }
       provedorService.Guardar(provedor);
       return "redirect:/api/provedor";
    }
    
    @GetMapping("/api/provedor/editar/{idProvedor}")
    public String editarProvedor(Provedor provedor, Model model){
     provedor = provedorService.BuscarProvedor(provedor);
     model.addAttribute("provedor", provedor);
     return "/provedor/modificar";
    }
    
    
}
