package com.tienda1.domain;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.Data;
import jakarta.persistence.Id;

@Data
@Entity
@Table(name="categoria")
public class Categoria implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_categoria")
    private Long idCategoria;
    
    //@Column(name="descripcion")
    private String descripcion;
    
    //@Column(name="ruta_imagen")
    private String rutaImagen;
    
    //@Column(name="activo")
    private boolean activo;
    
    public Categoria() {
        
    }
    
    public Categoria(String categoria, boolean activo){
        this.descripcion = categoria;
        this.activo= activo;
    }
    
}
