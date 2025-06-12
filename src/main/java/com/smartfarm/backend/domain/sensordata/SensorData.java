package com.smartfarm.backend.domain.sensordata;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Document(collection = "sensor_data") 
public class SensorData {

    @Id
    private String id;

    private String deviceId;
    private double temperature;
    private double humidity;
    private double light;
    private double soilMoisture;
    private LocalDateTime timestamp;

    public SensorData(String deviceId, double temperature, double humidity, double light, double soilMoisture, LocalDateTime timestamp) {
        this.deviceId = deviceId;
        this.temperature = temperature;
        this.humidity = humidity;
        this.light = light;
        this.soilMoisture = soilMoisture;
        this.timestamp = timestamp;
    }

    // 기본 생성자 (MongoDB용)
    protected SensorData() {}

}
