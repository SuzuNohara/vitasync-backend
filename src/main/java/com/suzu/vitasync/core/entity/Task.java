package com.suzu.vitasync.core.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tarea", schema = "vitasync")
public class Task {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

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

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    public enum Prioridad { baja, media, alta, critica }
    @Enumerated(EnumType.STRING)
    @Column(name = "prioridad")
    private Prioridad prioridad;

    public enum Dificultado { facil, media, dificil }
    @Enumerated(EnumType.STRING)
    @Column(name = "dificultado")
    private Dificultado dificultado;

    public enum Estado { Pendiente, En_progreso, Hecho, Cancelado }
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "id")
    private User usuario;

    @ManyToOne
    @JoinColumn(name = "dependencia", referencedColumnName = "id")
    private Task dependencia;

    @ManyToOne
    @JoinColumn(name = "subtarea_de", referencedColumnName = "id")
    private Task subtareaDe;

    @ManyToOne
    @JoinColumn(name = "categoria", referencedColumnName = "id")
    private Categoria categoria;

}