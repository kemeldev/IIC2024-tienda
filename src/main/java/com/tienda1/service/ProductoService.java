package com.tienda1.service;

import com.tienda1.domain.Producto;
import java.util.List;

public interface ProductoService {
    
    //se obtiene un listado de productos en un List
    public List<Producto> getProductos(boolean activos);
    
    // se recupera un registro de la tabla producto en un objeto de tipo producto
    // si el idproducto pasado por parámetro existe, sino se retorn un null
    
    public Producto getProducto(Producto producto);
    
    // se inserta un nuevo registro en la tabla producto... si el objeto pasado no tiene un idProducto
    // se actualiza un registro de la tabla producto... si el objeto pasado tiene un idProducto y este existe en la tabla
    public void save(Producto producto);
    
    // elimina un registro de la tabla producto.. si el objeto pasado tiene un idProducto y este existe en la tabla
    public void delete(Producto producto);
}
