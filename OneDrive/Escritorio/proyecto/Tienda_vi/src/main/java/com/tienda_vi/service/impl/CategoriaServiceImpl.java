
package com.tienda_vi.service.impl;

import com.tienda_vi.dao.CategoriaDao;
import com.tienda_vi.domain.Categoria;
import com.tienda_vi.service.CategoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl implements CategoriaService{
    
    @Autowired
    private CategoriaDao categoriaDao;

    @Override
    public List<Categoria> getCategoria(boolean activos) {
       var lista=categoriaDao.findAll();
       if (activos) {
           lista.removeIf( c -> !c.isActivo());
       }
       
       return lista;
    }
    
}
