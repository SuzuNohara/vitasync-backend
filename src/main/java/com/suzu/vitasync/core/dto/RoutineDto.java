package com.suzu.vitasync.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoutineDto {
    private Integer id;
    private String nombreRutina;
    private String descripcionRutina;
    private LocalTime horaInicioRutina;
    private Integer duracionRutinaMinutos;
    private String repeticion; // e.g., "L,MA,MI"
    private Boolean activa;
    private Integer usuario;
}