package com.tienda_vi.controller;

import com.tienda_vi.domain.Categoria;
import com.tienda_vi.service.CategoriaService;
import com.tienda_vi.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pruebas")
public class PruebasController {
    
    @Autowired
    private ProductoService productoService;
    @Autowired
    private CategoriaService categoriaService;
    
    @GetMapping("/listado")
    public String listado(Model model) {
        
    var lista = productoService.getProductos(false);
        
        model.addAttribute("productos",lista);
        
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("categorias",categorias);
        
        return "/pruebas/listado";
        
    }
    
    @GetMapping("/listado/{idCategoria}")
    public String modifica(Categoria categoria, Model model){
        categoria=categoriaService.getCategoria(categoria);
        var lista=categoria.getProductos();
        model.addAttribute("productos", lista);
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("categorias",categorias);
        return "/puebas/listado";
    }
    
    @GetMapping("/listado2")
    public String listado2(Model model) {
        
    var lista = productoService.getProductos(false);
        
        model.addAttribute("productos",lista);
                        
        return "/pruebas/listado2";
    }
    
    @PostMapping("/query1")
    public String consultaJPA(@RequestParam(value = "precioInf") double precioInf, @RequestParam(value = "precioSup") double precioSup, Model model) {
        var lista = productoService.consultaJPA(precioInf, precioSup);
        model.addAttribute("productos", lista);
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        
        return "/pruebas/listado2";
    }
    
    @PostMapping("/query2")
    public String consultaJPQL(@RequestParam(value = "precioInf") double precioInf, @RequestParam(value = "precioSup") double precioSup, Model model) {
        var lista = productoService.consultaJPQL(precioInf, precioSup);
        model.addAttribute("productos", lista);
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        
        return "/pruebas/listado2";
    }
    
    @PostMapping("/query3")
    public String consultaSQL(@RequestParam(value = "precioInf") double precioInf, @RequestParam(value = "precioSup") double precioSup, Model model) {
        var lista = productoService.consultaSQL(precioInf, precioSup);
        model.addAttribute("productos", lista);
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        
        return "/pruebas/listado2";
    }
    
    @GetMapping("/practica4")
public String practica4(Model model) {
    var lista = productoService.getProductos(false);
    model.addAttribute("productos", lista);
    return "/pruebas/practica4";
}

@PostMapping("/practica4")
public String consultaJPApractica(@RequestParam(value = "existenciasMen") double existenciasMen, @RequestParam(value = "existenciasMay") double existenciasMay, Model model) {
    var lista = productoService.consultaJPA(existenciasMen, existenciasMay);
    model.addAttribute("productos", lista);
    model.addAttribute("existenciasMen", existenciasMen);
    model.addAttribute("existenciasMay", existenciasMay);
    return "pruebas/practica4";
}
   
}