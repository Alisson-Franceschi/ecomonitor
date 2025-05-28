package com.ecotrack.ecomonitor.entity;

import com.ecotrack.ecomonitor.enums.StatusEstacao;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Estacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private double latitude;
    private double longitude;

    @Enumerated(EnumType.STRING)
    private StatusEstacao status;

    private LocalDate dataInstalacao;

    @OneToMany(mappedBy = "estacao", cascade = CascadeType.ALL)
    private List<Sensor> sensores;
}
