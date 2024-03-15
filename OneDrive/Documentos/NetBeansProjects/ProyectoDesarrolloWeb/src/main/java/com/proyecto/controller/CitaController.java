
package com.proyecto.controller;

import com.proyecto.domain.Cita;
import com.proyecto.service.FirebaseStorageService;
import com.proyecto.service.CitaService;
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
@RequestMapping("/cita")
public class CitaController {
    @Autowired
    private CitaService citaService;
    
    @GetMapping("/listado")
    public String listado(Model model) { 
        var lista = citaService.getCitas(false);
        
        model.addAttribute("citas", lista);
        model.addAttribute("totalCitas", lista.size());
        
        return "cita/listado"; // Corregido
    }
    
    @Autowired
    private FirebaseStorageService firebaseStorageService; 
    
    @PostMapping("/guardar")
    public String save(Cita cita, @RequestParam("imagenFile") MultipartFile imagenFile) {
        
        if (!imagenFile.isEmpty()) {
            String ruta = firebaseStorageService.cargaImagen(imagenFile, "cita", cita.getIdCita());
            cita.setRutaImagen(ruta);
        }
        
        citaService.save(cita); // Guardar una vez es suficiente
        return "redirect:/cita/listado";
    }
    
    @GetMapping("/modificar/{idcita}")
    public String modifica(@PathVariable("idcita") Long idCita, Model model) {
    Cita cita = citaService.getCitaById(idCita);
    
    if (cita != null) {
        model.addAttribute("cita", cita);
        return "cita/modifica";
    } else {
        // Manejar el caso en que la categoría no se encuentre
        return "redirect:/cita/listado";
    }
}
    
    @GetMapping("/eliminar/{idcita}")
    public String elimina(@PathVariable("idcita") Long idCita) {
    Cita cita = new Cita();
    cita.setIdCita(idCita);
    citaService.delete(cita);
    return "redirect:/cita/listado";
}
}

