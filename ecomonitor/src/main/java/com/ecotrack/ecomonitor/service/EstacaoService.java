package com.ecotrack.ecomonitor.service;

import com.ecotrack.ecomonitor.entity.Estacao;
import com.ecotrack.ecomonitor.entity.Sensor;
import com.ecotrack.ecomonitor.entity.enums.Status;
import com.ecotrack.ecomonitor.repository.EstacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EstacaoService {

    private EstacaoRepository estacaoRepository;

    public EstacaoService(EstacaoRepository estacaoRepository) {
        this.estacaoRepository = estacaoRepository;
    }

    @Transactional
    public Estacao cadastrarEstacao(Estacao estacao) {

        if(!estacao.getSensores().isEmpty()) {
            for (Sensor sensor : estacao.getSensores()) {
                sensor.setEstacao(estacao);
            }
        }

        estacao.setStatus(Status.ATIVA);
        estacao.setDataInstalacao(LocalDate.now());

        return estacaoRepository.save(estacao);
    }

    public List<Estacao> listarTodas() {
        return estacaoRepository.findAll();
    }

    public Estacao buscarPorId(Long id) {
        return estacaoRepository.findById(id).orElse(null);
    }

    public List<Estacao> listarPorStatus(Status status) {
        return estacaoRepository.findByStatus(status);
    }

    public void deletarEstacao(Long id) {
        estacaoRepository.deleteById(id);
    }




    public void desativarEstacaoAleatorio(){
        List<Estacao> estacoes = estacaoRepository.findAll();
        if (estacoes.isEmpty()) {
            System.out.println("Nenhuma estação encontrada para ser desativada!");
            return;
        }

        Estacao estacaoAleatoria = estacoes.get((int) (Math.random() * estacoes.size()));
        estacaoAleatoria.setStatus(Status.INATIVA);
        estacaoAleatoria.setDataInativacao(LocalDateTime.now());
        System.out.println("Estação " + estacaoAleatoria.getNome() + ", ID = " + estacaoAleatoria.getId() + " foi desativada!");
    }

    public void verificarEstacoesInativas() {
        List<Estacao> inativas = listarPorStatus(Status.INATIVA);

        for (Estacao estacao : inativas) {
            if (estacao.getDataInativacao() != null) {
                Duration tempo = Duration.between(estacao.getDataInativacao(), LocalDateTime.now());

                if (tempo.toMinutes() >= 2) {
                    System.out.println("ALERTA: Estação " + estacao.getNome() +
                            ", ID = " + estacao.getId() + " está inativa há " +
                            tempo.toMinutes() + " minutos. ISSO É INACEITÁVEL!");

                    System.out.println(">> Enviando informação ao serviço de manutenção externa...\n");
                }
            }
        }
    }
}
