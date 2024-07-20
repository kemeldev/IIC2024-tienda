package com.tienda1.controller;

import com.tienda1.domain.Categoria;
import com.tienda1.service.CategoriaService;
import com.tienda1.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    
    
    @GetMapping("/listado2")
    public String listado2(Model model) {
        
        var productos=productoService.getProductos(false);
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());
        return "/pruebas/listado2";
    }
    
    @PostMapping("/query1")
    public String consultaQuery1(
            @RequestParam(value="precioInf") double precioInf,
            @RequestParam(value="precioSup") double precioSup,
            Model model) {
        
        var productos=productoService.findByPrecioBetweenOrderByDescripcion(precioInf, precioSup);
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);

        return "/pruebas/listado2";
    }
    
    
}
