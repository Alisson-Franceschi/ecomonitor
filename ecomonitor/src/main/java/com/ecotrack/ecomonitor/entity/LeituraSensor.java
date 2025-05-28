package com.ecotrack.ecomonitor.entity;

import com.ecotrack.ecomonitor.enums.TipoSensor;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class LeituraSensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Sensor sensor;

    private float valor;
    private LocalDateTime timestamp = LocalDateTime.now();

    private boolean alerta;
    private String mensagemAlerta;
}
