
package com.tienda_vi.dao;

import com.tienda_vi.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductoDao extends JpaRepository<Producto,Long>  {
//Se define una consulta tipo JPA para recuperar los productos que se 
    //encuentran en un rango de precios ordenados por descripcion acsendente
    public List<Producto> findByPrecioBetweenOrderByDescripcion(double precioInf, double precioSup);
    
    //Se define una consulta tipo JPQL para recuperar los productos que se 
    //encuentran en un rango de precios ordenados por descripcion acsendente
    @Query(value="SELECT p FROM Producto p WHERE p.precio BETWEEN :precioInf and :precioSup ORDER BY p.descripcion ASC")
    public List<Producto> consultaJPQL(double precioInf, double precioSup);
    
    //Se define una consulta tipo SQL para recuperar los productos que se 
    //encuentran en un rango de precios ordenados por descripcion acsendente
    @Query(nativeQuery=true,value="SELECT * FROM producto p WHERE p.precio BETWEEN :precioInf and :precioSup ORDER BY p.descripcion ASC")
    public List<Producto> consultaSQL(double precioInf, double precioSup);
    
    //Se define una consulta tipo JPA para recuperar los productos que se 
    //encuentran en un rango de precios ordenados por descripcion acsendente
    public List<Producto> findByExistenciasBetweenOrderByDescripcion(double existenciasMen, double existenciasMay);
}
