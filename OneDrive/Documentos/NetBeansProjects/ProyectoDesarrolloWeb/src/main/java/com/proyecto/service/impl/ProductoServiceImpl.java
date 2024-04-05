
package com.proyecto.service.impl;

import com.proyecto.dao.ProductoDao;
import com.proyecto.domain.Producto;
import com.proyecto.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProductoServiceImpl implements ProductoService {
 
    @Autowired
    private ProductoDao productoDao;
    
    @Override
    @Transactional(readOnly=true)
    public List<Producto> getProductos(boolean activos) {
        var lista=productoDao.findAll();
        
        if (activos) { //Si se quieren sólo las productos activas
            lista.removeIf(c -> !c.isActivo());
        }
        
        return lista;
    }

    @Override
    @Transactional(readOnly=true)
    public Producto getProducto(Producto producto) {
       
        return productoDao.findById(producto.getIdProducto()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Producto producto) {
        
        productoDao.save(producto);
    }

    @Override
    @Transactional
    public void delete(Producto producto) {
        
       productoDao.delete(producto);
    }

    @Override
    public Producto getProductoById(Long idProducto) {
       return productoDao.findById(idProducto).orElse(null);
    }
    //Se define una consulta tipo JPA para recuperar los productos que se 
    //encuentran en un rango de precios ordenados por descripcion acsendente
    @Override
    @Transactional(readOnly=true)
    public List<Producto> consultaJPA(double precioInf, double precioSup){
        return productoDao.findByPrecioBetweenOrderByDescripcion(precioInf, precioSup);
    }
    
    //Se define una consulta tipo JPQL para recuperar los productos que se 
    //encuentran en un rango de precios ordenados por descripcion acsendente
    @Override
    @Transactional(readOnly=true)
    public List<Producto> consultaJPQL(double precioInf, double precioSup){
        return productoDao.consultaJPQL(precioInf, precioSup);
    }
    
    //Se define una consulta tipo SQL para recuperar los productos que se 
    //encuentran en un rango de precios ordenados por descripcion acsendente
    @Override
    @Transactional(readOnly=true)
    public List<Producto> consultaSQL(double precioInf, double precioSup){
        return productoDao.consultaSQL(precioInf, precioSup);
    }
    
}