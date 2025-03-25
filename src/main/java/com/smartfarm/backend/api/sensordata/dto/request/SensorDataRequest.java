package com.smartfarm.backend.api.sensordata.dto.request;

import lombok.Getter;

@Getter
public class SensorDataRequest {
    private double temperature;
    private double humidity;
    private double light_level;     // 아두이노에서 오는 JSON에 맞춰서 snake_case 사용
    private double soil_moisture;

    public double getLight() {
        return light_level;
    }

    public double getSoilMoisture() {
        return soil_moisture;
    }
}
