
package com.proyecto.service.impl;

import com.proyecto.dao.CategoriaDao;
import com.proyecto.domain.Categoria;
import com.proyecto.service.CategoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CategoriaServiceImpl implements CategoriaService {
 
    @Autowired
    private CategoriaDao categoriaDao;
    
    @Override
    @Transactional(readOnly=true)
    public List<Categoria> getCategorias(boolean activos) {
        var lista=categoriaDao.findAll();
        
        if (activos) { //Si se quieren sólo las categorias activas
            lista.removeIf(c -> !c.isActivo());
        }
        
        return lista;
    }

    @Override
    @Transactional(readOnly=true)
    public Categoria getCategoria(Categoria categoria) {
       
        return categoriaDao.findById(categoria.getIdCategoria()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Categoria categoria) {
        
        categoriaDao.save(categoria);
    }

    @Override
    @Transactional
    public void delete(Categoria categoria) {
        
       categoriaDao.delete(categoria);
    }

    @Override
    public Categoria getCategoriaById(Long idCategoria) {
       return categoriaDao.findById(idCategoria).orElse(null);
    }
    
}