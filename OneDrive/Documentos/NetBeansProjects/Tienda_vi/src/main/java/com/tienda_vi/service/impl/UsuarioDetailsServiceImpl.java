package com.tienda_vi.service.impl;

import com.tienda_vi.dao.UsuarioDao;
import com.tienda_vi.domain.Rol;
import com.tienda_vi.domain.Usuario;
import com.tienda_vi.service.UsuarioDetailsService;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("userDetailsService")
public class UsuarioDetailsServiceImpl implements UsuarioDetailsService, UserDetailsService {
    
    @Autowired
    private UsuarioDao usuarioDao;
    
    @Autowired
    private HttpSession session;
    
    @Override
    @Transactional (readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        //Busca un usuario enla table de usuarios..
        Usuario usuario = usuarioDao.findByUsername(username);
        
        //se valida si se recupero un usuario
        if (usuario==null) {
            throw new UsernameNotFoundException(username);
        }
                
        //Acá tenemos acceso a la imagen
        session.removeAttribute("imagenUsuario");
        session.setAttribute("imagenUsuario", usuario.getRutaImagen());
        
        //si estamos acá...  se encontro el usuario
        var roles = new ArrayList<GrantedAuthority>();
        
        for (Rol r : usuario.getRoles()) {
            roles.add(new SimpleGrantedAuthority(r.getNombre()));
        }
        
        //Acá ya se tiene la info del usuario...
        return new User(usuario.getUsername(),usuario.getPassword(),roles);
        
        
    }
    
    
}
