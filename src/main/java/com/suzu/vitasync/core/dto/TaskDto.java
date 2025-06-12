package com.suzu.vitasync.core.dto;

import com.suzu.vitasync.core.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private Integer id;
    private String nombreTarea;
    private String descripcionTarea;
    private LocalDateTime fechaCreacionTarea;
    private LocalDate fechaEntregaTarea;
    private LocalDateTime fechaInicioTarea;
    private LocalDateTime fechaFinTarea;
    private LocalDateTime fechaActualizacion;
    private Task.Prioridad prioridad;
    private Task.Dificultado dificultado;
    private Task.Estado estado;
    private Integer usuarioId;
    private Integer dependencia;
    private Integer subtareaDe;
    private Integer categoria;
}