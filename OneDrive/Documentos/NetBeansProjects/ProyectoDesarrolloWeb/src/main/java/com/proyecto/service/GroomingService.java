
package com.proyecto.service;

import com.proyecto.domain.Grooming;
import java.util.List;


public interface GroomingService {
    public List<Grooming> getGroomings(boolean activos);
    
    public Grooming  getGrooming(Grooming grooming);
    
    public Grooming getGroomingById(Long idGrooming);
    
    public void save(Grooming grooming);
    
    public void delete(Grooming grooming);
}
