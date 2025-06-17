package com.suzu.vitasync.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaEventoDto {
    private Integer id;
    private String nombre;
    private String color;
}