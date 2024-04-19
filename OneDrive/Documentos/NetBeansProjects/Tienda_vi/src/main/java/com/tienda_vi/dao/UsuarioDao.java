package com.tienda_vi.dao;

import com.tienda_vi.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDao extends JpaRepository<Usuario,Long>{
    
    public Usuario findByUsername(String username);

    public Usuario findByUsernameAndPassword(String username, String password);

    public boolean existsByUsernameOrCorreo(String username, String correo);

    public Usuario findByUsernameOrCorreo(String username, String correo);
    
}
