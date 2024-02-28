
package com.tienda_vi.dao;

import com.tienda_vi.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoDao extends JpaRepository<Producto,Long>  {

}
