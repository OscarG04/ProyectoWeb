package com.proyecto.controller;


import com.proyecto.service.ProductoService;
import com.proyecto.service.CategoriaService;
import com.proyecto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


public class IndexController {
    
    @Autowired
    private ProductoService productoService;
    
    
    @GetMapping("/")
    public String listado(Model model) { 
        var lista = productoService.getProductos(false);
        model.addAttribute("productos", lista);
        return "index"; // Corregido
    }
   
}

