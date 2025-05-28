package com.ecotrack.ecomonitor.repository;

import com.ecotrack.ecomonitor.entity.Estacao;
import com.ecotrack.ecomonitor.enums.StatusEstacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstacaoRepository extends JpaRepository<Estacao, Long> {
    List<Estacao> findByStatus(StatusEstacao status);
}
