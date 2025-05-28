package com.ecotrack.ecomonitor.repository;

import com.ecotrack.ecomonitor.entity.Sensor;
import com.ecotrack.ecomonitor.enums.TipoSensor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorRepository {
    List<Sensor> findByTipo(TipoSensor tipo);
}
