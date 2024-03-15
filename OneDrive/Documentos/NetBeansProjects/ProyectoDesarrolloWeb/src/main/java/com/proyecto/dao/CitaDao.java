
package com.proyecto.dao;


import com.proyecto.domain.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitaDao extends JpaRepository<Cita, Long> {
    
}
