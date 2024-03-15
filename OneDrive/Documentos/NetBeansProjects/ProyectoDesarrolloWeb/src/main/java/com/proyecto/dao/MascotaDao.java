
package com.proyecto.dao;

import com.proyecto.domain.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MascotaDao extends JpaRepository<Mascota, Long> {
    
}