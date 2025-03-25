package com.smartfarm.backend.application;

import com.smartfarm.backend.api.sensordata.dto.request.SensorDataRequest;
import com.smartfarm.backend.api.sensordata.dto.response.SensorDataResponse;
import com.smartfarm.backend.domain.sensordata.SensorData;
import com.smartfarm.backend.domain.sensordata.SensorDataRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SensorDataService {

    private final SensorDataRepository sensorDataRepository;

    public SensorDataService(SensorDataRepository sensorDataRepository) {
        this.sensorDataRepository = sensorDataRepository;
    }

    public SensorDataResponse saveSensorData(SensorDataRequest request) {
        SensorData sensorData = new SensorData(
            request.getTemperature(),
            request.getHumidity(),
            request.getLight(),
            request.getSoilMoisture(),
            LocalDateTime.now()
        );

        SensorData saved = sensorDataRepository.save(sensorData);

        return SensorDataResponse.builder()
            .id(saved.getId())
            .temperature(saved.getTemperature())
            .humidity(saved.getHumidity())
            .light(saved.getLight())
            .soilMoisture(saved.getSoilMoisture())
            .timestamp(saved.getTimestamp())
            .build();
    }

    public List<SensorDataResponse> getAllSensorData() {
        return sensorDataRepository.findAll().stream()
            .map(sensorData -> SensorDataResponse.builder()
                .id(sensorData.getId())
                .temperature(sensorData.getTemperature())
                .humidity(sensorData.getHumidity())
                .light(sensorData.getLight())
                .soilMoisture(sensorData.getSoilMoisture())
                .timestamp(sensorData.getTimestamp())
                .build())
            .collect(Collectors.toList());
    }
}
