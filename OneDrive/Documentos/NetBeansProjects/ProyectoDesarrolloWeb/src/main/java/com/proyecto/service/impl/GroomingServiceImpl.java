
package com.proyecto.service.impl;

import com.proyecto.dao.GroomingDao;
import com.proyecto.domain.Grooming;
import com.proyecto.service.GroomingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class GroomingServiceImpl implements GroomingService {
 
    @Autowired
    private GroomingDao groomingDao;
    
    @Override
    @Transactional(readOnly=true)
    public List<Grooming> getGroomings(boolean activos) {
        var lista=groomingDao.findAll();
        
        if (activos) { //Si se quieren sólo las groomings activas
            lista.removeIf(c -> !c.isActivo());
        }
        
        return lista;
    }

    @Override
    @Transactional(readOnly=true)
    public Grooming getGrooming(Grooming grooming) {
       
        return groomingDao.findById(grooming.getIdGrooming()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Grooming grooming) {
        
        groomingDao.save(grooming);
    }

    @Override
    @Transactional
    public void delete(Grooming grooming) {
        
       groomingDao.delete(grooming);
    }

    @Override
    public Grooming getGroomingById(Long idGrooming) {
       return groomingDao.findById(idGrooming).orElse(null);
    }
    
}
    

