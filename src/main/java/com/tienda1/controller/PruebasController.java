package com.tienda1.controller;

import com.tienda1.domain.Categoria;
import com.tienda1.service.CategoriaService;
import com.tienda1.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pruebas")
public class PruebasController {
    
    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private CategoriaService categoriaService;
    
    @GetMapping("/listado")
    public String listado(Model model) {
        
        var productos=productoService.getProductos(false);
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());
        
        var categorias=categoriaService.getCategorias(false);
        model.addAttribute("categorias", categorias);

        
        return "/pruebas/listado";
    }
    
    
    @GetMapping("/listado/{idCategoria}")
    public String listado(Model model, Categoria categoria) {
        
        var productos=categoriaService.getCategoria(categoria).getProductos();
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());
        
        var categorias=categoriaService.getCategorias(false);
        model.addAttribute("categorias", categorias);

        
        return "/pruebas/listado";
    }
}
