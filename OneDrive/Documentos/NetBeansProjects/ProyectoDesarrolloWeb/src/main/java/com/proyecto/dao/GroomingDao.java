
package com.proyecto.dao;


import com.proyecto.domain.Grooming;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroomingDao extends JpaRepository<Grooming, Long> {
    
}
