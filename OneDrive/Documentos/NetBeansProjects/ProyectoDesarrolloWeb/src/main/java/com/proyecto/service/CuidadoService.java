package com.proyecto.service;

import com.proyecto.domain.Cuidado;
import java.util.List;


public interface CuidadoService {
    public List<Cuidado> getCuidados(boolean activos);
    
    public Cuidado  getCuidado(Cuidado cuidado);
    
    public Cuidado getCuidadoById(Long idCuidado);
    
    public void save(Cuidado cuidado);
    
    public void delete(Cuidado cuidado);
}
