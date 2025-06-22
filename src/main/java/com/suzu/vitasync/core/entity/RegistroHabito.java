package com.suzu.vitasync.core.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "registro_habito")
public class RegistroHabito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "habito")
    private Habito habito;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "realizado")
    private Boolean realizado;
}