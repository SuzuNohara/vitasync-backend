package com.suzu.vitasync.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "evento")
public class Evento {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre_evento", length = 45)
    private String nombreEvento;

    @Column(name = "descripcion_evento", length = 200)
    private String descripcionEvento;

    @Column(name = "ubicacion_evento", length = 200)
    private String ubicacionEvento;

    @Column(name = "fecha_inicio_evento")
    private LocalDateTime fechaInicioEvento;

    @Column(name = "fecha_fin_evento")
    private LocalDateTime fechaFinEvento;

    @Column(name = "allday")
    private Boolean allday;

    @ManyToOne
    @JoinColumn(name = "repetir")
    private RepeticionEvento repetir;

    @ManyToOne
    @JoinColumn(name = "categoria")
    private CategoriaEvento categoria;

    @ManyToOne
    @JoinColumn(name = "usuario")
    private User usuario;

}