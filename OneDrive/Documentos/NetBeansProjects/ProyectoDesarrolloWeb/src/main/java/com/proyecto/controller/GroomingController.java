
package com.proyecto.controller;

import com.proyecto.domain.Grooming;
import com.proyecto.service.FirebaseStorageService;
import com.proyecto.service.GroomingService;
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
@RequestMapping("/grooming")
public class GroomingController {
    @Autowired
    private GroomingService groomingService;
    
    @GetMapping("/listado")
    public String listado(Model model) { 
        var lista = groomingService.getGroomings(false);
        
        model.addAttribute("groomings", lista);
        model.addAttribute("totalGroomings", lista.size());
        
        return "grooming/listado"; // Corregido
    }
    
    @Autowired
    private FirebaseStorageService firebaseStorageService; 
    
    @PostMapping("/guardar")
    public String save(Grooming grooming, @RequestParam("imagenFile") MultipartFile imagenFile) {
        
        if (!imagenFile.isEmpty()) {
            String ruta = firebaseStorageService.cargaImagen(imagenFile, "grooming", grooming.getIdGrooming());
            grooming.setRutaImagen(ruta);
        }
        
        groomingService.save(grooming); // Guardar una vez es suficiente
        return "redirect:/grooming/listado";
    }
    
    @GetMapping("/modificar/{idgrooming}")
    public String modifica(@PathVariable("idgrooming") Long idGrooming, Model model) {
    Grooming grooming = groomingService.getGroomingById(idGrooming);
    
    if (grooming != null) {
        model.addAttribute("grooming", grooming);
        return "grooming/modifica";
    } else {
        // Manejar el caso en que la categoría no se encuentre
        return "redirect:/grooming/listado";
    }
}
    
    @GetMapping("/eliminar/{idgrooming}")
    public String elimina(@PathVariable("idgrooming") Long idGrooming) {
    Grooming grooming = new Grooming();
    grooming.setIdGrooming(idGrooming);
    groomingService.delete(grooming);
    return "redirect:/grooming/listado";
}
}

