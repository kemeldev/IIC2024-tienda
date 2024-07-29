package com.tienda1.dao;

import com.tienda1.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDao extends JpaRepository <Usuario,Long> {
    
    public Usuario findByUsername(String username);
}
