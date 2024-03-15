package com.proyecto.dao;

import com.proyecto.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {

// Se hace una busqueda por username 
    public Usuario findByUsername(String username);
    
    //Asi busacamos el usuario de distintas formas

    Usuario findByUsernameAndPassword(String username, String Password);

    Usuario findByUsernameOrCorreo(String username, String correo);

    boolean existsByUsernameOrCorreo(String username, String correo);
}

