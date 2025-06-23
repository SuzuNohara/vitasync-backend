package com.suzu.vitasync.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categoria_transaccion")
public class CategoriaTransaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @Column(name = "icono", length = 45)
    private String icono;

    @Column(name = "color", length = 7)
    private String color;
}
