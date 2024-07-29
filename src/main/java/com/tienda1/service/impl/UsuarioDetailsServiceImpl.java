package com.tienda1.service.impl;

import com.tienda1.dao.UsuarioDao;
import com.tienda1.domain.Rol;
import com.tienda1.domain.Usuario;
import com.tienda1.service.UsuarioDetailsService;
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
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) 
            throws UsernameNotFoundException {
        
        // se busca el usuario en la tabla usuarios por medio del username
        Usuario usuario = usuarioDao.findByUsername(username);
                
        // se valida si se encontró el usuario con el username pasado...
        
        if(usuario == null){
            // el usuario no se encontró
            throw new UsernameNotFoundException(username);
        }
        // si pasamos aca es porque si se encontró el usuario
        // guardamos la imagen del usuario en una variable de sesion
        session.removeAttribute("imagenUsuario");
        session.setAttribute("imagenUsuario", usuario.getRutaImagen());
        
        // se deben recuperar los roles del usuario y crear un ArrayListo con Roles de seguridad
        var roles = new ArrayList<GrantedAuthority>();
        
        // se revisan los roles del usuario y se convierten en roles de seguridad
        for (Rol r: usuario.getRoles()){
            roles.add(new SimpleGrantedAuthority(r.getNombre()));
        }
        
        // se retorna un usuario de Seguridad con roles incluidos...
        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }
    
}
