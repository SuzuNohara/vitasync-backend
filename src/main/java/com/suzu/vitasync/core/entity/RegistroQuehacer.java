package com.suzu.vitasync.core.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "registro_quehacer")
public class RegistroQuehacer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "quehacer")
    private Quehacer quehacer;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(name = "estado")
    private Boolean estado;
}