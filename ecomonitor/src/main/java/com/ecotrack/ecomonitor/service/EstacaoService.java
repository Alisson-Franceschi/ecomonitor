package com.ecotrack.ecomonitor.service;

import com.ecotrack.ecomonitor.entity.Estacao;
import com.ecotrack.ecomonitor.entity.Sensor;
import com.ecotrack.ecomonitor.enums.StatusEstacao;
import com.ecotrack.ecomonitor.repository.EstacaoRepository;
import com.ecotrack.ecomonitor.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EstacaoService {

    private EstacaoRepository estacaoRepository;

    public EstacaoService(EstacaoRepository estacaoRepository) {
        this.estacaoRepository = estacaoRepository;
    }

    public Estacao cadastrarEstacao(Estacao estacao) {

        if(!estacao.getSensores().isEmpty()) {
            for (Sensor sensor : estacao.getSensores()) {
                sensor.setEstacao(estacao);
            }
        }

        estacao.setStatus(StatusEstacao.ATIVA);
        estacao.setDataInstalacao(LocalDate.now());
        return estacaoRepository.save(estacao);
    }

    public List<Estacao> listarTodas() {
        return estacaoRepository.findAll();
    }

    public Estacao buscarPorId(Long id) {
        return estacaoRepository.findById(id).orElse(null);
    }

    public List<Estacao> listarPorStatus(StatusEstacao status) {
        return estacaoRepository.findByStatus(status);
    }

    public void deletarEstacao(Long id) {
        estacaoRepository.deleteById(id);
    }
}
