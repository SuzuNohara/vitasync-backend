package com.suzu.vitasync.core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "emocion")
public class Emocion {

    public enum Tipo {
        POSITIVA, NEGATIVA, NEUTRA
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "hora")
    private LocalTime hora;

    @Column(name = "emocion", length = 45)
    private String emocion;

    @Column(name = "intensidad")
    private Integer intensidad;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private Tipo tipo;

    @Column(name = "color", length = 7)
    private String color;

    @ManyToOne
    @JoinColumn(name = "usuario")
    private User usuario;
}
