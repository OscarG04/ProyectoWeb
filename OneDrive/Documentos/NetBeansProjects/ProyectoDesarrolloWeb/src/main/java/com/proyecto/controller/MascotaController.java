package com.proyecto.controller;

import com.proyecto.domain.Mascota;
import com.proyecto.service.FirebaseStorageService;
import com.proyecto.service.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/mascota")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = mascotaService.getMascotas();
        model.addAttribute("mascotas", lista);
        model.addAttribute("totalMascotas", lista.size());
        return "mascota/listado";
    }

    @PostMapping("/guardar")
    public String save(Mascota mascota, @RequestParam("imagenFile") MultipartFile imagenFile) {
        if (!imagenFile.isEmpty()) {
            String ruta = firebaseStorageService.cargaImagen(imagenFile, "mascota", mascota.getIdMascota());
            mascota.setRutaImagen(ruta);
        }
        mascotaService.save(mascota);
        return "redirect:/mascota/listado";
    }

    @GetMapping("/modificar/{idMascota}")
    public String modifica(@PathVariable("idMascota") Long idMascota, Model model) {
        Mascota mascota = mascotaService.getMascota(idMascota);
        if (mascota != null) {
            model.addAttribute("mascota", mascota);
            return "mascota/modifica";
        } else {
            // Manejar el caso en que la mascota no se encuentre
            return "redirect:/mascota/listado";
        }
    }

    @GetMapping("/eliminar/{idMascota}")
    public String elimina(@PathVariable("idMascota") Long idMascota) {
        mascotaService.delete(idMascota);
        return "redirect:/mascota/listado";
    }
}
