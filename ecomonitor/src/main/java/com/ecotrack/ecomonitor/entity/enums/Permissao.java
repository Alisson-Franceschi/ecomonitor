package com.ecotrack.ecomonitor.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Permissao {

    ADMIN(1, "ADMIN"),
    USER(2, "USER");

    private Integer id;
    private String descricao;
}
