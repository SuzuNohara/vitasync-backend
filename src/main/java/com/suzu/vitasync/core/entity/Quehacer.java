package com.suzu.vitasync.core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "quehacer")
public class Quehacer {

    public enum Frecuencia {
        UNICA, DIARIA, SEMANAL, MENSUAL
    }

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @Column(name = "descripccion", length = 200)
    private String descripccion;

    @Enumerated(EnumType.STRING)
    @Column(name = "frecuencia")
    private Frecuencia frecuencia;

    @Column(name = "dias", length = 45)
    private String dias; // Comma-separated values for SET

    @Column(name = "activo")
    private Boolean activo;

    @ManyToOne
    @JoinColumn(name = "usuario")
    private User usuario;
}