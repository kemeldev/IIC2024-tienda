package com.tienda1.service;

import com.tienda1.domain.Categoria;
import java.util.List;

public interface CategoriaService {
    
    //se obtiene un listado de categorias en un List
    public List<Categoria> getCategorias(boolean activos);
    
}
