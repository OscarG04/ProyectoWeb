
package com.proyecto.controller;

import com.proyecto.domain.Categoria;
import com.proyecto.service.CategoriaService;
import com.proyecto.service.FirebaseStorageService;
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
@RequestMapping("/categoria")
public class CategoriaController {
    
    @Autowired
    private CategoriaService categoriaService;
    
    @GetMapping("/listado")
    public String listado(Model model) { 
        var lista = categoriaService.getCategorias(false);
        
        model.addAttribute("categorias", lista);
        model.addAttribute("totalCategorias", lista.size());
        
        return "categoria/listado"; // Corregido
    }
    
    @Autowired
    private FirebaseStorageService firebaseStorageService; 
    
    @PostMapping("/guardar")
    public String save(Categoria categoria, @RequestParam("imagenFile") MultipartFile imagenFile) {
        
        if (!imagenFile.isEmpty()) {
            String ruta = firebaseStorageService.cargaImagen(imagenFile, "categoria", categoria.getIdCategoria());
            categoria.setRutaImagen(ruta);
        }
        
        categoriaService.save(categoria); // Guardar una vez es suficiente
        return "redirect:/categoria/listado";
    }
    
    @GetMapping("/modificar/{idcategoria}")
    public String modifica(@PathVariable("idcategoria") Long idCategoria, Model model) {
    Categoria categoria = categoriaService.getCategoriaById(idCategoria);
    
    if (categoria != null) {
        model.addAttribute("categoria", categoria);
        return "categoria/modifica";
    } else {
        // Manejar el caso en que la categoría no se encuentre
        return "redirect:/categoria/listado";
    }
}
    
    @GetMapping("/eliminar/{idcategoria}")
    public String elimina(@PathVariable("idcategoria") Long idCategoria) {
    Categoria categoria = new Categoria();
    categoria.setIdCategoria(idCategoria);
    categoriaService.delete(categoria);
    return "redirect:/categoria/listado";
}
}

