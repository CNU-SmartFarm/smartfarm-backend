package com.smartfarm.backend.domain.plant;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate; // YYYY-MM-DD 형식에 맞춰 LocalDate 사용

@Getter
@NoArgsConstructor // MongoDB가 객체를 인스턴스화하는 데 필요한 기본 생성자
@Document(collection = "plants") // MongoDB 컬렉션 이름 지정
public class Plant {

    @Id
    private String id;

    private String name;
    private String species;
    private LocalDate registeredDate; // "YYYY-MM-DD" 형식에 맞춰 LocalDate 사용

    private double optimalTempMin;
    private double optimalTempMax;
    private double optimalHumidityMin;
    private double optimalHumidityMax;
    private double optimalSoilMoistureMin;
    private double optimalSoilMoistureMax;
    private double optimalLightMin;
    private double optimalLightMax;

    public Plant(String name, String species, LocalDate registeredDate,
        double optimalTempMin, double optimalTempMax,
        double optimalHumidityMin, double optimalHumidityMax,
        double optimalSoilMoistureMin, double optimalSoilMoistureMax,
        double optimalLightMin, double optimalLightMax) {
        this.name = name;
        this.species = species;
        this.registeredDate = registeredDate;
        this.optimalTempMin = optimalTempMin;
        this.optimalTempMax = optimalTempMax;
        this.optimalHumidityMin = optimalHumidityMin;
        this.optimalHumidityMax = optimalHumidityMax;
        this.optimalSoilMoistureMin = optimalSoilMoistureMin;
        this.optimalSoilMoistureMax = optimalSoilMoistureMax;
        this.optimalLightMin = optimalLightMin;
        this.optimalLightMax = optimalLightMax;
    }
}