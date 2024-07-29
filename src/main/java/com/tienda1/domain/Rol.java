package com.tienda1.domain;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.Data;
import jakarta.persistence.Id;
import java.util.List;

@Data
@Entity
@Table(name="rol")
public class Rol implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_rol")
    private Long idRol;
    private String nombre;
    @Column(name="id_usuario")
    private Long idUsuario;
    
}
