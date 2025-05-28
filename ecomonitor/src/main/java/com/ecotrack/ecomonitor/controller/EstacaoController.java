package com.ecotrack.ecomonitor.controller;

import com.ecotrack.ecomonitor.entity.Estacao;
import com.ecotrack.ecomonitor.enums.StatusEstacao;
import com.ecotrack.ecomonitor.service.EstacaoService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return ResponseEntity.ok(estacaoService.cadastrarEstacao(estacao));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Estacao>> listarTodas() {

        List<Estacao> estacoes = estacaoService.listarTodas();
        if (estacoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(estacaoService.listarTodas());
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
    public ResponseEntity<List<Estacao>> buscarPorStatus(@PathVariable StatusEstacao status) {

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
