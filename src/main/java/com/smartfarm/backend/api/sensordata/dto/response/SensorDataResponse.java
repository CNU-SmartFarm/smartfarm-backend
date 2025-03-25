package com.smartfarm.backend.api.sensordata.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class SensorDataResponse {
    private String id;
    private double temperature;
    private double humidity;
    private double light;
    private double soilMoisture;
    private LocalDateTime timestamp;
}
