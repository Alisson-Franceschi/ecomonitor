package com.ecotrack.ecomonitor.controller;

import com.ecotrack.ecomonitor.entity.Sensor;
import com.ecotrack.ecomonitor.entity.enums.TipoSensor;
import com.ecotrack.ecomonitor.service.EstacaoService;
import com.ecotrack.ecomonitor.service.SensorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sensores")
public class SensorController {

    private final EstacaoService estacaoService;
    private SensorService sensorService;

    public SensorController(SensorService sensorService, EstacaoService estacaoService) {
        this.sensorService = sensorService;
        this.estacaoService = estacaoService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Sensor> Cadastrar(@RequestBody Sensor sensor) {
        Sensor sensorSalvo = sensorService.CadastrarSensor(sensor);

        return ResponseEntity.status(HttpStatus.CREATED).body(sensorSalvo);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Sensor>> Listar() {
        List<Sensor> sensores = sensorService.listarSensores();

        if(sensores.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(sensores);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Sensor> buscarPorId(@PathVariable Long id) {
        Sensor sensor = sensorService.buscarSensor(id);

        if(sensor == null){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(sensor);
    }

    @GetMapping("/buscar/tipo_sensor/{tipoSensor}")
    public ResponseEntity<List<Sensor>> buscarPorTipoSensor(@PathVariable TipoSensor tipoSensor) {
        List<Sensor> sensores = sensorService.buscarSensoresPorTipo(tipoSensor);

        if(sensores.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(sensores);
    }

    @DeleteMapping
    public ResponseEntity<Sensor> Deletar(@PathVariable Long id) {
        Sensor sensor = sensorService.buscarSensor(id);
        if(sensor == null){
            return ResponseEntity.noContent().build();
        }
        sensorService.removerSensor(id);

        return ResponseEntity.noContent().build();
    }
}
