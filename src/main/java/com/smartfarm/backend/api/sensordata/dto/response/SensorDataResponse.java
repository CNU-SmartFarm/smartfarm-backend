package com.smartfarm.backend.api.sensordata.dto.response;

import com.smartfarm.backend.domain.sensordata.SensorData;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class SensorDataResponse {
    private String id;
    private String deviceId;
    private double temperature;
    private double humidity;
    private double light;
    private double soilMoisture;
    private LocalDateTime timestamp;

    public static SensorDataResponse from(SensorData data) {
        return SensorDataResponse.builder()
            .id(data.getId())
            .deviceId(data.getDeviceId())
            .temperature(data.getTemperature())
            .humidity(data.getHumidity())
            .light(data.getLight())
            .soilMoisture(data.getSoilMoisture())
            .timestamp(data.getTimestamp())
            .build();
    }
}
