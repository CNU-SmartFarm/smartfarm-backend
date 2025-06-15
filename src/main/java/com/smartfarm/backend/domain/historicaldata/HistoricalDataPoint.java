package com.smartfarm.backend.domain.historicaldata;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Document(collection = "historical_data_points") // MongoDB 컬렉션 이름 지정
public class HistoricalDataPoint {

    @Id
    private String id; // 명세에는 "string"으로 되어 있지만, MongoDB ObjectId를 위해 String 사용

    private String plantId;
    private String date; // 명세에 맞춰 String 유지 (예: "2025-06-12")
    private int time; // 명세에 맞춰 Number 유지 (예: 1400 - 14시 00분)

    private double temperature;
    private double humidity;
    private double soilMoisture;
    private double light;

    public HistoricalDataPoint(String plantId, String date, int time, double temperature, double humidity, double soilMoisture, double light) {
        this.plantId = plantId;
        this.date = date;
        this.time = time;
        this.temperature = temperature;
        this.humidity = humidity;
        this.soilMoisture = soilMoisture;
        this.light = light;
    }
}