package com.suzu.vitasync.core.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pasos")
public class Pasos {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "orden_paso")
    private Integer ordenPaso;

    @Column(name = "nombre_paso", length = 45)
    private String nombrePaso;

    @Column(name = "descripcion_paso", length = 200)
    private String descripcionPaso;

    @Column(name = "duracion_paso_minutos")
    private Integer duracionPasoMinutos;

    @ManyToOne
    @JoinColumn(name = "rutina")
    private Routine rutina;
}