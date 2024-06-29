package com.tienda1.service;

import com.tienda1.domain.Categoria;
import java.util.List;

public interface CategoriaService {
    
    //se obtiene un listado de categorias en un List
    public List<Categoria> getCategorias(boolean activos);
    
    // se recupera un registro de la tabla categoria en un objeto de tipo categoria
    // si el idcategoria pasado por parámetro existe, sino se retorn un null
    
    public Categoria getCategoria(Categoria categoria);
    
    // se inserta un nuevo registro en la tabla categoria... si el objeto pasado no tiene un idCategoria
    // se actualiza un registro de la tabla categoria... si el objeto pasado tiene un idCategoria y este existe en la tabla
    public void save(Categoria categoria);
    
    // elimina un registro de la tabla categoria.. si el objeto pasado tiene un idCategoria y este existe en la tabla
    public void delete(Categoria categoria);
}
