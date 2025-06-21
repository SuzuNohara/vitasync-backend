// src/main/java/com/suzu/vitasync/core/entity/RegistroRutina.java
package com.suzu.vitasync.core.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "registro_rutina")
public class RegistroRutina {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "rutina")
    private Routine rutina;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(name = "hora_inicio")
    private LocalTime horaInicio;

    @Column(name = "pasos_completados")
    private Integer pasosCompletados;
}