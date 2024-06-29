package com.tienda1.dao;

import com.tienda1.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoDao extends JpaRepository <Producto,Long> {
    
}
