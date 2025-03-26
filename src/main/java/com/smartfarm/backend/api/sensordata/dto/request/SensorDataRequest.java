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

    // 테스트용 setter
//    public void setTemperature(double temperature) { this.temperature = temperature; }
//    public void setHumidity(double humidity) { this.humidity = humidity; }
//    public void setLight_level(double light_level) { this.light_level = light_level; }
//    public void setSoil_moisture(double soil_moisture) { this.soil_moisture = soil_moisture; }
}
