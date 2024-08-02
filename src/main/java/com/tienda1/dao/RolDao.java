package com.tienda1.dao;

import com.tienda1.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolDao extends JpaRepository <Rol,Long> {
    
    
}
