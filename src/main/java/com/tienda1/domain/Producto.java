package com.tienda1.domain;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.Data;
import jakarta.persistence.Id;

@Data
@Entity
@Table(name="producto")
public class Producto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_producto")
    private Long idProducto;
    
    // private Long idCategoria;  //eliminamos este atributo
    
    private String detalle;
    
    private double precio;

    private int existencias;

    //@Column(name="descripcion")
    private String descripcion;
    
    //@Column(name="ruta_imagen")
    private String rutaImagen;
    
    //@Column(name="activo")
    private boolean activo;
    
    
    @ManyToOne // La asociacion de muchos a uno
    @JoinColumn(name="id_categoria") //Indicar el atributo en este caso de la tabla
    
    private Categoria categoria;
    
    public Producto(){
        
    }
    
    public Producto(String producto, boolean activo){
        this.descripcion = producto;
        this.activo = activo;
    }
    
    
    
}
