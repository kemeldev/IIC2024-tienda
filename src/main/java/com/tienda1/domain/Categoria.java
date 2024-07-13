package com.tienda1.domain;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.Data;
import jakarta.persistence.Id;
import java.util.List;

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
    
    @OneToMany // Es una asociacion para una relacion de uno a muchos entre tablas
    @Column(name="id_categoria") // es la columna con la que se realiza la asociacion
    private List<Producto> productos;
    
    public Categoria(){
        
    }
    
    public Categoria(String categoria, boolean activo){
        this.descripcion = categoria;
        this.activo = activo;
    }
    
}
