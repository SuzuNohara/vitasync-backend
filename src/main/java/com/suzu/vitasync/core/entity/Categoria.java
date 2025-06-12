package com.suzu.vitasync.core.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "categoria")
public class Categoria {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre_categoria", length = 100)
    private String nombreCategoria;

    @Column(name = "color_categoria", length = 7)
    private String colorCategoria;
}