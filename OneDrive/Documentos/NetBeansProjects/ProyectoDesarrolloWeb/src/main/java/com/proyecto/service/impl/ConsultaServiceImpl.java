
package com.proyecto.service.impl;

import com.proyecto.dao.ConsultaDao;
import com.proyecto.domain.Consulta;
import com.proyecto.service.ConsultaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ConsultaServiceImpl implements ConsultaService {
 
    @Autowired
    private ConsultaDao consultaDao;
    
    @Override
    @Transactional(readOnly=true)
    public List<Consulta> getConsultas(boolean activos) {
        var lista=consultaDao.findAll();
        
        if (activos) { //Si se quieren sólo las consultas activas
            lista.removeIf(c -> !c.isActivo());
        }
        
        return lista;
    }

    @Override
    @Transactional(readOnly=true)
    public Consulta getConsulta(Consulta consulta) {
       
        return consultaDao.findById(consulta.getIdConsulta()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Consulta consulta) {
        
        consultaDao.save(consulta);
    }

    @Override
    @Transactional
    public void delete(Consulta consulta) {
        
       consultaDao.delete(consulta);
    }

    @Override
    public Consulta getConsultaById(Long idConsulta) {
       return consultaDao.findById(idConsulta).orElse(null);
    }
    
}