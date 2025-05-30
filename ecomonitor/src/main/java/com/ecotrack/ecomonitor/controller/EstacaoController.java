package com.ecotrack.ecomonitor.controller;

import com.ecotrack.ecomonitor.entity.Estacao;
import com.ecotrack.ecomonitor.entity.enums.Status;
import com.ecotrack.ecomonitor.service.EstacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estacoes")
public class EstacaoController {

    private EstacaoService estacaoService;

    public EstacaoController(EstacaoService estacaoService) {
        this.estacaoService = estacaoService;
    }

    @PostMapping("/criar")
    public ResponseEntity<Estacao> criar(@RequestBody Estacao estacao) {
        Estacao estacaoSalva = estacaoService.cadastrarEstacao(estacao);

        return ResponseEntity.status(HttpStatus.CREATED).body(estacaoSalva);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Estacao>> listarTodas() {

        List<Estacao> estacoes = estacaoService.listarTodas();
        if (estacoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(estacoes);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Estacao> buscarPorId(@PathVariable Long id) {
        Estacao estacao = estacaoService.buscarPorId(id);
        if (estacao == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(estacao);
    }

    @GetMapping("/buscar/status/{status}")
    public ResponseEntity<List<Estacao>> buscarPorStatus(@PathVariable Status status) {

        List<Estacao> estacoes = estacaoService.listarPorStatus(status);
        if (estacoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(estacoes);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Estacao> deletar(@PathVariable Long id) {
        Estacao estacao = estacaoService.buscarPorId(id);
        if (estacao == null) {
            return ResponseEntity.notFound().build();
        }
        estacaoService.deletarEstacao(id);

        return ResponseEntity.noContent().build();
    }
}
