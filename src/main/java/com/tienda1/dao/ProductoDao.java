package com.tienda1.dao;

import com.tienda1.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductoDao extends JpaRepository <Producto,Long> {
    
    // Ejemplo de una consulta ampliada
    public List<Producto> findByPrecioBetweenOrderByDescripcion(double precioInf, double precioSup);
    
    // Ejemplo de una consulta ampliada utilizando JPQL (producto se refiere a la clase producto por eso en Mayuscula Producto)
    @Query(value="SELECT a FROM Producto a WHERE a.precio BETWEEN :precioInf AND :precioSup ORDER BY a.descripcion ASC")
    public List<Producto> metodoJPQL(double precioInf, double precioSup);
    
    // Ejemplo de una consulta ampliada utilizando SQL nativo (p de producto en minuscula porque es la tabla)
    @Query(nativeQuery=true, value="SELECT * FROM producto a WHERE a.precio BETWEEN :precioInf AND :precioSup ORDER BY a.descripcion ASC")
    public List<Producto> metodoSQL(double precioInf, double precioSup);
}
