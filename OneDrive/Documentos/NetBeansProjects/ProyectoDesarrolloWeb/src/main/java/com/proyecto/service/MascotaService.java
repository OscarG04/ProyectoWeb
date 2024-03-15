
package com.proyecto.service;

import com.proyecto.dao.MascotaDao;
import com.proyecto.domain.Mascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface MascotaService {

    // Obtiene un listado de todas las mascotas
    List<Mascota> getMascotas();
    
    // Obtiene una mascota por su ID
    Mascota getMascota(Long idMascota);
    
    // Guarda o actualiza una mascota
    void save(Mascota mascota);
    
    // Elimina una mascota por su ID
    void delete(Long idMascota);
}