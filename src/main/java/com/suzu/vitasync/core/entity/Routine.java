package com.suzu.vitasync.core.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;

@Data
@Entity
@Table(name = "rutina")
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre_rutina", nullable = false)
    private String nombreRutina;

    @Column(name = "descripcion_rutina")
    private String descripcionRutina;

    @Column(name = "hora_inicio_rutina")
    private java.time.LocalTime horaInicioRutina;

    @Column(name = "duracion_rutina_minutos")
    private Integer duracionRutinaMinutos;

    @Column(name = "repeticion")
    private String repeticion; // D, L, MA, MI, J, V, S

    @Column(name = "activa")
    private Boolean activa;

    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "id")
    private User usuario;
}