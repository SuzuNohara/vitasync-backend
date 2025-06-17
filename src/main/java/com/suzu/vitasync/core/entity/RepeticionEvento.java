package com.suzu.vitasync.core.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "repeticion_evento")
public class RepeticionEvento {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "dia_repeticion")
    private Integer diaRepeticion;

    @Column(name = "semana_repeticion")
    private Integer semanaRepeticion;

    @Column(name = "mes_repeticion")
    private Integer mesRepeticion;

    @Column(name = "anio_repeticion")
    private Integer anioRepeticion;

    @Column(name = "dias_repeticion", length = 45)
    private String diasRepeticion;

    @Column(name = "fin")
    private LocalDateTime fin;

    @Column(name = "forever")
    private Boolean forever;
}