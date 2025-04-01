package com.smartfarm.backend.application;

import com.smartfarm.backend.api.sensordata.dto.request.SensorDataRequest;
import com.smartfarm.backend.api.sensordata.dto.response.SensorDataResponse;
import com.smartfarm.backend.domain.sensordata.SensorData;
import com.smartfarm.backend.domain.sensordata.SensorDataRepository;
import java.util.List;
import java.util.NoSuchElementException;
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
            request.getDeviceId(),
            request.getTemperature(),
            request.getHumidity(),
            request.getLight(),
            request.getSoilMoisture(),
            LocalDateTime.now()
        );

        SensorData saved = sensorDataRepository.save(sensorData);

        return SensorDataResponse.builder()
            .id(saved.getId())
            .deviceId(saved.getDeviceId())
            .temperature(saved.getTemperature())
            .humidity(saved.getHumidity())
            .light(saved.getLight())
            .soilMoisture(saved.getSoilMoisture())
            .timestamp(saved.getTimestamp())
            .build();
    }

    public List<SensorDataResponse> getAllSensorData() {
        return sensorDataRepository.findAll().stream()
            .map(SensorDataResponse::from)
            .collect(Collectors.toList());
    }

    public SensorDataResponse getLatestSensorData() {
        SensorData latest = sensorDataRepository.findTopByOrderByTimestampDesc()
            .orElseThrow(() -> new IllegalStateException("센서 데이터가 없습니다."));
        return SensorDataResponse.from(latest);
    }

    public List<SensorDataResponse> getSensorDataByDeviceId(String deviceId) {
        List<SensorData> dataList = sensorDataRepository.findByDeviceId(deviceId);

        if (dataList.isEmpty()) {
            throw new NoSuchElementException("해당 디바이스의 데이터가 없습니다: " + deviceId);
        }

        return dataList.stream()
            .map(SensorDataResponse::from)
            .collect(Collectors.toList());
    }

    public SensorDataResponse getLatestSensorDataByDeviceId(String deviceId) {
        return sensorDataRepository.findTopByDeviceIdOrderByTimestampDesc(deviceId)
            .map(SensorDataResponse::from)
            .orElseThrow(() -> new NoSuchElementException("해당 디바이스의 최신 데이터가 없습니다: " + deviceId));
    }
}
