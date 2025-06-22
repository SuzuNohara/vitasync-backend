package com.suzu.vitasync.core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "objetivo_ahorro")
public class ObjetivoAhorro {

    public enum Prioridad {
        ALTA, MEDIA, BAJA
    }

    public enum Estado {
        ACTIVO, COMPLETADO, CANCELADO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @Column(name = "descipcion", length = 200)
    private String descipcion;

    @Column(name = "objetivo", precision = 10, scale = 2)
    private BigDecimal objetivo;

    @Column(name = "monto", precision = 10, scale = 2)
    private BigDecimal monto;

    @Column(name = "inicio")
    private LocalDate inicio;

    @Column(name = "limite")
    private LocalDate limite;

    @Enumerated(EnumType.STRING)
    @Column(name = "prioridad")
    private Prioridad prioridad;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "usuario")
    private User usuario;

    @ManyToOne
    @JoinColumn(name = "transaccion")
    private Transaccion transaccion;
}