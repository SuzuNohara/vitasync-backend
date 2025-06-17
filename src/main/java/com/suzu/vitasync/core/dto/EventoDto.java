package com.suzu.vitasync.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventoDto {
    private Integer id;
    private String nombreEvento;
    private String descripcionEvento;
    private String ubicacionEvento;
    private LocalDateTime fechaInicioEvento;
    private LocalDateTime fechaFinEvento;
    private Boolean allday;
    private Integer repetirId;
    private Integer categoriaId;
    private Integer usuarioId;
}