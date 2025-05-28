package com.ecotrack.ecomonitor.service;

import com.ecotrack.ecomonitor.entity.Estacao;
import com.ecotrack.ecomonitor.entity.LeituraSensor;
import com.ecotrack.ecomonitor.enums.TipoSensor;
import com.ecotrack.ecomonitor.repository.EstacaoRepository;
import com.ecotrack.ecomonitor.repository.LeituraSensorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LeituraSensorService {

    private LeituraSensorRepository leituraSensorRepository;
    private EstacaoRepository estacaoRepository;

    public LeituraSensor registrarLeitura(Long estacaoId, LeituraSensor leitura) {
        Estacao estacaoOpt = estacaoRepository.findById(estacaoId);
        if (estacaoOpt.isEmpty()) {
            throw new RuntimeException("Estação não encontrada");
        }

        Estacao estacao = estacaoOpt;
        leitura.setEstacao(estacao);
        leitura.setTimestamp(LocalDateTime.now());

        // Lógica de alerta simples
        if (isValorForaDoPadrao(leitura)) {
            leitura.setAlerta(true);
            leitura.setMensagemAlerta("Valor fora do intervalo esperado para " + leitura.getTipo());
        }

        return leituraSensorRepository.save(leitura);
    }

    public List<LeituraSensor> listarPorEstacaoETipoEPeriodo(Long estacaoId, TipoSensor tipo, LocalDateTime inicio, LocalDateTime fim) {
        return leituraSensorRepository.findByEstacaoIdAndTipoAndTimestampBetween(estacaoId, tipo, inicio, fim);
    }

    public List<LeituraSensor> listarPorEstacao(Long estacaoId) {
        return leituraSensorRepository.findByEstacaoId(estacaoId);
    }

    private boolean isValorForaDoPadrao(LeituraSensor leitura) {
        float valor = leitura.getValor();
        return switch (leitura.getTipo()) {
            case TEMPERATURA -> valor < -10 || valor > 45;
            case UMIDADE -> valor < 10 || valor > 90;
            case CO2 -> valor > 1000;
            case RUIDO -> valor > 85;
        };
    }
}
