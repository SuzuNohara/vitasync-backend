package com.suzu.vitasync.core.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;

@Data
@Entity
@Table(name = "rutinas")
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_rutina", nullable = false)
    private String nombreRutina;

    @Column(name = "descripcion_rutina")
    private String descripcionRutina;

    @Column(name = "hora_inicio_rutina")
    private LocalTime horaInicioRutina;

    @Column(name = "duracion_rutina_minutos")
    private Integer duracionRutinaMinutos;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;
}