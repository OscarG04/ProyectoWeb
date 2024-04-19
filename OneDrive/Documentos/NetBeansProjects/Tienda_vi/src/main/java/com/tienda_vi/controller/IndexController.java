package com.tienda_vi.controller;

import com.tienda_vi.domain.Producto;
import com.tienda_vi.service.CategoriaService;
import com.tienda_vi.service.ProductoService;
import com.tienda_vi.service.FirebaseStorageService;
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

