
package com.proyecto.controller;


import com.proyecto.domain.Consulta;
import com.proyecto.service.ConsultaService;
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
@RequestMapping("/consulta")
public class ConsultaController {
    
    @Autowired
    private ConsultaService consultaService;
    
    @GetMapping("/listado")
    public String listado(Model model) { 
        var lista = consultaService.getConsultas(false);
        
        model.addAttribute("consultas", lista);
        model.addAttribute("totalConsultas", lista.size());
        
        return "consulta/listado"; // Corregido
    }
    
    @Autowired
    private FirebaseStorageService firebaseStorageService; 
    @PostMapping("/guardar")
    public String save(Consulta consulta, @RequestParam("imagenFile") MultipartFile imagenFile) {
        
        if (!imagenFile.isEmpty()) {
            String ruta = firebaseStorageService.cargaImagen(imagenFile, "consulta", consulta.getIdConsulta());
            consulta.setRutaImagen(ruta);
        }
        
        consultaService.save(consulta); // Guardar una vez es suficiente
        return "redirect:/consulta/listado";
    }
 
    
    @GetMapping("/modificar/{idconsulta}")
    public String modifica(@PathVariable("idconsulta") Long idConsulta, Model model) {
    Consulta consulta = consultaService.getConsultaById(idConsulta);
    
    if (consulta != null) {
        model.addAttribute("consulta", consulta);
        return "consulta/modifica";
    } else {
        // Manejar el caso en que la categoría no se encuentre
        return "redirect:/consulta/listado";
    }
}
    
    @GetMapping("/eliminar/{idconsulta}")
    public String elimina(@PathVariable("idconsulta") Long idConsulta) {
    Consulta consulta = new Consulta();
    consulta.setIdConsulta(idConsulta);
    consultaService.delete(consulta);
    return "redirect:/consulta/listado";
}
}

