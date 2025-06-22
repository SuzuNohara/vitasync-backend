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
@Table(name = "transaccion")
public class Transaccion {

    public enum Tipo {
        INGRESO, EGRESO
    }

    public enum Frecuencia {
        DIARIA, SEMANAL, MENSUAL, ANUAL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private Tipo tipo;

    @ManyToOne
    @JoinColumn(name = "categoria")
    private CategoriaTransaccion categoria;

    @Column(name = "monto", precision = 10, scale = 2)
    private BigDecimal monto;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "descripcion", length = 200)
    private String descripcion;

    @Column(name = "recurrencia")
    private Boolean recurrencia;

    @ManyToOne
    @JoinColumn(name = "usuario")
    private User usuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "frecuencia")
    private Frecuencia frecuencia;

    @Column(name = "fin")
    private LocalDate fin;
}
