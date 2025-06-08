package com.suzu.vitasync.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private Long id;
    private String nombreTarea;
    private String descripcionTarea;
    private String fechaCreacionTarea;
    private String fechaEntregaTarea;
    private String fechaInicioTarea;
    private String fechaFinTarea;

}