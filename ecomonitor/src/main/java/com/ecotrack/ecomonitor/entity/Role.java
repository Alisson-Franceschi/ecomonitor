package com.ecotrack.ecomonitor.entity;

import com.ecotrack.ecomonitor.entity.enums.Permissao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Permissao permissao;

    public Role() {}

}
