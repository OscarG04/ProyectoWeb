
package com.proyecto.service;

import com.proyecto.domain.Cita;
import java.util.List;


public interface CitaService {
    public List<Cita> getCitas(boolean activos);
    
    public Cita  getCita(Cita cita);
    
    public Cita getCitaById(Long idCita);
    
    public void save(Cita cita);
    
    public void delete(Cita cita);
}
