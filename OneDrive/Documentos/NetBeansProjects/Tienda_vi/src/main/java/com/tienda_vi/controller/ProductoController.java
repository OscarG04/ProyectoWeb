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

@Controller
@RequestMapping("/producto")
public class ProductoController {
    
    @Autowired
    private ProductoService productoService;
    @Autowired
    private CategoriaService categoriaService;
    
    @GetMapping("/listado")
    public String listado(Model model) { 
        var lista = productoService.getProductos(false);
        
        model.addAttribute("productos", lista);
        model.addAttribute("totalProductos", lista.size());
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("categorias", categorias);
        return "producto/listado"; // Corregido
    }
    
    @Autowired
    private FirebaseStorageService firebaseStorageService; // Corregido el nombre del servicio
    
    @PostMapping("/guardar")
    public String save(Producto producto, @RequestParam("imagenFile") MultipartFile imagenFile) {
        
        if (!imagenFile.isEmpty()) {
            String ruta = firebaseStorageService.cargaImagen(imagenFile, "producto", producto.getIdProducto());
            producto.setRutaImagen(ruta);
        }
        
        productoService.save(producto); // Guardar una vez es suficiente
        return "redirect:/producto/listado";
    }
    
    @GetMapping("/modificar/{idproducto}")
    public String modifica(@PathVariable("idproducto") Long idProducto, Model model) {
    Producto producto = productoService.getProductoById(idProducto);
    
    if (producto != null) {
        model.addAttribute("producto", producto);
        return "producto/modifica";
    } else {
        // Manejar el caso en que la categoría no se encuentre
        return "redirect:/producto/listado";
    }
}
    
    @GetMapping("/eliminar/{idproducto}")
    public String elimina(@PathVariable("idproducto") Long idProducto) {
    Producto producto = new Producto();
    producto.setIdProducto(idProducto);
    productoService.delete(producto);
    return "redirect:/producto/listado";
}
}

