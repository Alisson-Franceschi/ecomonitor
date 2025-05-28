package com.ecotrack.ecomonitor.entity;

import com.ecotrack.ecomonitor.enums.TipoSensor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Estacao estacao;

    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoSensor tipo;

    @OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL)
    private List<LeituraSensor> leituras;
}
