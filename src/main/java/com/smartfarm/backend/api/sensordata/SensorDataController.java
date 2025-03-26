package com.smartfarm.backend.api.sensordata;

import com.smartfarm.backend.api.sensordata.dto.request.SensorDataRequest;
import com.smartfarm.backend.api.sensordata.dto.response.SensorDataResponse;
import com.smartfarm.backend.application.SensorDataService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sensorData")
public class SensorDataController {

    private final SensorDataService sensorDataService;

    public SensorDataController(SensorDataService sensorDataService) {
        this.sensorDataService = sensorDataService;
    }

    // 데이터 전체 조회
    @GetMapping
    public ResponseEntity<List<SensorDataResponse>> getAllSensorData() {
        List<SensorDataResponse> responseList = sensorDataService.getAllSensorData();
        return ResponseEntity.ok(responseList);
    }

    // 가장 최근 센서 데이터만 조회
    @GetMapping("/latest")
    public ResponseEntity<SensorDataResponse> getLatestSensorData() {
        SensorDataResponse latest = sensorDataService.getLatestSensorData();
        return ResponseEntity.ok(latest);
    }

    @PostMapping
    public ResponseEntity<SensorDataResponse> saveSensorData(@RequestBody SensorDataRequest request) {
        SensorDataResponse response = sensorDataService.saveSensorData(request);
        return ResponseEntity.ok(response);
    }
}
