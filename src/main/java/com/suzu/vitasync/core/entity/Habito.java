package com.suzu.vitasync.core.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "habito")
public class Habito {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @Column(name = "descipcion", length = 200)
    private String descipcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "frecuencia")
    private Frecuencia frecuencia;

    @Column(name = "objetivo")
    private Integer objetivo;

    @Column(name = "inicio")
    private LocalDate inicio;

    @Column(name = "fin")
    private LocalDate fin;

    @Column(name = "activo")
    private Boolean activo;

    @ManyToOne
    @JoinColumn(name = "usuario")
    private User usuario;

    public enum Frecuencia {
        DIARIA, SEMANAL, MENSUAL, ANUAL
    }
}