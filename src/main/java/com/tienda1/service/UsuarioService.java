package com.tienda1.service;

import com.tienda1.domain.Usuario;
import java.util.List;

public interface UsuarioService {
    
    //se obtiene un listado de usuarios en un List
    public List<Usuario> getUsuarios();
    
    //se obtiene un usuario a partir del id del un usuario
    public Usuario getUsuario(Usuario usuario);
    
    //se obtiene un Usuario a partir del username de un usuario
    public Usuario getUsuarioPorUsername(String usernameusuario);
    
    //se obtiene un Usuario a partir del username y el password de un usuario
    public Usuario getUsuarioPorUsernameYPassword(String username, String password);
    
    //se obtiene un Usuario a partir del username o el correo de un usuario
    public Usuario getUsuarioPorUsernameOCorreo(String username, String correo);
    
    //se obtiene verdadero si un Usuario existe a partir del username o el correo de un usuario
    public boolean existsUsuarioPorUsernameOCorreo(String username, String correo);
    
    // se inserta un nuevo registro en la tabla usuario... si el objeto pasado no tiene un idUsuario
    // se actualiza un registro de la tabla usuario... si el objeto pasado tiene un idUsuario y este existe en la tabla
    public void save(Usuario usuario, boolean creaRol);
    
    // elimina un registro de la tabla usuario.. si el objeto pasado tiene un idUsuario y este existe en la tabla
    public void delete(Usuario usuario);
    
    
}
