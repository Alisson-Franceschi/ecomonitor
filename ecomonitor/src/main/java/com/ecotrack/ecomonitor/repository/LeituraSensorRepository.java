package com.ecotrack.ecomonitor.repository;

import com.ecotrack.ecomonitor.entity.LeituraSensor;
import com.ecotrack.ecomonitor.enums.TipoSensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LeituraSensorRepository extends JpaRepository<LeituraSensor, Long> {

    List<LeituraSensor> findByEstacaoIdAndTipoAndTimestampBetween(
            Long estacaoId, TipoSensor tipo, LocalDateTime inicio, LocalDateTime fim);


    List<LeituraSensor> findByEstacaoId(Long estacaoId);
}
