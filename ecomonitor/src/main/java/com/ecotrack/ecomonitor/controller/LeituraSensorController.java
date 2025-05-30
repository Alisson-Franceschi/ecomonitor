package com.ecotrack.ecomonitor.controller;

import com.ecotrack.ecomonitor.entity.LeituraSensor;
import com.ecotrack.ecomonitor.entity.enums.TipoSensor;
import com.ecotrack.ecomonitor.service.LeituraSensorService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/leituras")
public class LeituraSensorController {

    private LeituraSensorService leituraSensorService;

    public LeituraSensorController(LeituraSensorService leituraSensorService) {
        this.leituraSensorService = leituraSensorService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<LeituraSensor> registrarLeitura(@RequestBody LeituraSensor leitura) {
        LeituraSensor leituraSalva = leituraSensorService.registrarLeitura(leitura);

        return ResponseEntity.status(HttpStatus.CREATED).body(leituraSalva);
    }

    @GetMapping("/buscar/sensor/{sensorId}")
    public ResponseEntity<List<LeituraSensor>> listarPorSensor(@PathVariable Long sensorId) {
        return ResponseEntity.ok(leituraSensorService.listarPorSensor(sensorId));
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<LeituraSensor>> filtrarPorEstacaoTipoPeriodo(
            @RequestParam Long estacaoId,
            @RequestParam TipoSensor tipo,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        List<LeituraSensor> leituras = leituraSensorService.listarPorEstacaoTipoPeriodo(estacaoId, tipo, inicio, fim);
        return ResponseEntity.ok(leituras);
    }

    @GetMapping("/estatisticas")
    public ResponseEntity<LeituraSensorService.EstatisticaLeituraDTO> getEstatisticas(
            @RequestParam Long estacaoId,
            @RequestParam TipoSensor tipo,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {

        LeituraSensorService.EstatisticaLeituraDTO estatisticas = leituraSensorService.obterEstatisticas(estacaoId, tipo, inicio, fim);
        return ResponseEntity.ok(estatisticas);
    }

    @GetMapping("/media-geral/estacao/{estacaoId}")
    public ResponseEntity<Double> obterMediaGeral(@PathVariable Long estacaoId) {
        return ResponseEntity.ok(leituraSensorService.obterMediaGeralPorEstacao(estacaoId));
    }
}
