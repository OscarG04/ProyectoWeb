package com.proyecto.service.impl;

import com.proyecto.dao.CuidadoDao;
import com.proyecto.domain.Cuidado;
import com.proyecto.service.CuidadoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CuidadoServiceImpl implements CuidadoService {
 
    @Autowired
    private CuidadoDao cuidadoDao;
    
    @Override
    @Transactional(readOnly=true)
    public List<Cuidado> getCuidados(boolean activos) {
        var lista=cuidadoDao.findAll();
        
        if (activos) { //Si se quieren sólo las cuidados activas
            lista.removeIf(c -> !c.isActivo());
        }
        
        return lista;
    }

    @Override
    @Transactional(readOnly=true)
    public Cuidado getCuidado(Cuidado cuidado) {
       
        return cuidadoDao.findById(cuidado.getIdCuidado()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Cuidado cuidado) {
        
        cuidadoDao.save(cuidado);
    }

    @Override
    @Transactional
    public void delete(Cuidado cuidado) {
        
       cuidadoDao.delete(cuidado);
    }

    @Override
    public Cuidado getCuidadoById(Long idCuidado) {
       return cuidadoDao.findById(idCuidado).orElse(null);
    }
    
}