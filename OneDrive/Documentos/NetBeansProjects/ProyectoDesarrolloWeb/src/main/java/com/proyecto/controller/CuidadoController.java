
package com.proyecto.controller;


import com.proyecto.domain.Cuidado;
import com.proyecto.service.CuidadoService;
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
@RequestMapping("/cuidado")
public class CuidadoController {
    
    @Autowired
    private CuidadoService cuidadoService;
    
    @GetMapping("/listado")
    public String listado(Model model) { 
        var lista = cuidadoService.getCuidados(false);
        
        model.addAttribute("cuidados", lista);
        model.addAttribute("totalCuidados", lista.size());
        
        return "cuidado/listado"; // Corregido
    }
    
    @Autowired
    private FirebaseStorageService firebaseStorageService; 
    @PostMapping("/guardar")
    public String save(Cuidado cuidado, @RequestParam("imagenFile") MultipartFile imagenFile) {
        
        if (!imagenFile.isEmpty()) {
            String ruta = firebaseStorageService.cargaImagen(imagenFile, "cuidado", cuidado.getIdCuidado());
            cuidado.setRutaImagen(ruta);
        }
        
        cuidadoService.save(cuidado); // Guardar una vez es suficiente
        return "redirect:/cuidado/listado";
    }
 
    
    @GetMapping("/modificar/{idcuidado}")
    public String modifica(@PathVariable("idcuidado") Long idCuidado, Model model) {
    Cuidado cuidado = cuidadoService.getCuidadoById(idCuidado);
    
    if (cuidado != null) {
        model.addAttribute("cuidado", cuidado);
        return "cuidado/modifica";
    } else {
        // Manejar el caso en que la categoría no se encuentre
        return "redirect:/cuidado/listado";
    }
}
    
    @GetMapping("/eliminar/{idcuidado}")
    public String elimina(@PathVariable("idcuidado") Long idCuidado) {
    Cuidado cuidado = new Cuidado();
    cuidado.setIdCuidado(idCuidado);
    cuidadoService.delete(cuidado);
    return "redirect:/cuidado/listado";
}
}