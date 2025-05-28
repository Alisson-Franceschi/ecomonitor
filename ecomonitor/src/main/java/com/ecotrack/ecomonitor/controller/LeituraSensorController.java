package com.ecotrack.ecomonitor.controller;

import com.ecotrack.ecomonitor.entity.LeituraSensor;
import com.ecotrack.ecomonitor.enums.TipoSensor;
import com.ecotrack.ecomonitor.service.LeituraSensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/leituras")
public class LeituraSensorController {

    @Autowired
    private LeituraSensorService leituraSensorService;

    @PostMapping("/{estacaoId}")
    public ResponseEntity<LeituraSensor> registrarLeitura(
            @PathVariable Long estacaoId,
            @RequestBody LeituraSensor leitura) {
        return ResponseEntity.ok(leituraSensorService.registrarLeitura(estacaoId, leitura));
    }

    @GetMapping("/estacao/{estacaoId}")
    public ResponseEntity<List<LeituraSensor>> listarPorEstacao(@PathVariable Long estacaoId) {
        return ResponseEntity.ok(leituraSensorService.listarPorEstacao(estacaoId));
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<LeituraSensor>> filtrarPorEstacaoTipoPeriodo(
            @RequestParam Long estacaoId,
            @RequestParam TipoSensor tipo,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim
    ) {
        return ResponseEntity.ok(leituraSensorService.listarPorEstacaoETipoEPeriodo(estacaoId, tipo, inicio, fim));
    }
}
