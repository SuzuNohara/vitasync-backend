package com.suzu.vitasync.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepeticionEventoDto {
    private Integer id;
    private Integer diaRepeticion;
    private Integer semanaRepeticion;
    private Integer mesRepeticion;
    private Integer anioRepeticion;
    private String diasRepeticion;
    private LocalDateTime fin;
    private Boolean forever;
}