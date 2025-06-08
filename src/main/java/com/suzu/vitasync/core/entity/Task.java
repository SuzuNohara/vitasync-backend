package com.suzu.vitasync.core.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "tareas")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_tarea", nullable = false)
    private String nombreTarea;

    @Column(name = "descripcion_tarea")
    private String descripcionTarea;

    @Column(name = "fecha_creacion_tarea")
    private LocalDateTime fechaCreacionTarea;

    @Column(name = "fecha_entrega_tarea")
    private LocalDate fechaEntregaTarea;

    @Column(name = "fecha_inicio_tarea")
    private LocalDateTime fechaInicioTarea;

    @Column(name = "fecha_fin_tarea")
    private LocalDateTime fechaFinTarea;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;
}