package com.smartfarm.backend.application;

import com.smartfarm.backend.api.sensordata.dto.request.SensorDataRequest;
import com.smartfarm.backend.api.sensordata.dto.response.SensorDataResponse;
import com.smartfarm.backend.domain.sensordata.SensorData;
import com.smartfarm.backend.domain.sensordata.SensorDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class SensorDataServiceTest {

    private SensorDataRepository sensorDataRepository;
    private SensorDataService sensorDataService;

    @BeforeEach
    void setUp() {
        sensorDataRepository = mock(SensorDataRepository.class);
        sensorDataService = new SensorDataService(sensorDataRepository);
    }
//
//    @Test
//    void saveSensorData_정상동작_확인() {
//        // given
//        SensorDataRequest request = new SensorDataRequest();
//        request.setTemperature(25.0);
//        request.setHumidity(60.0);
//        request.setLight_level(80.0);
//        request.setSoil_moisture(40.0);
//
//        // when
//        when(sensorDataRepository.save(any(SensorData.class))).thenAnswer(invocation -> {
//            SensorData input = invocation.getArgument(0);
//            return new SensorData(
//                input.getTemperature(),
//                input.getHumidity(),
//                input.getLight(),
//                input.getSoilMoisture(),
//                input.getTimestamp()
//            );
//        });
//
//        SensorDataResponse response = sensorDataService.saveSensorData(request);
//
//        // then
//        assertThat(response.getTemperature()).isEqualTo(25.0);
//        assertThat(response.getHumidity()).isEqualTo(60.0);
//        assertThat(response.getLight()).isEqualTo(80.0);
//        assertThat(response.getSoilMoisture()).isEqualTo(40.0);
//        assertThat(response.getTimestamp()).isNotNull();
//    }

    @Test
    void getAllSensorData_데이터없을때_빈리스트반환() {
        // given
        when(sensorDataRepository.findAll()).thenReturn(Collections.emptyList());

        // when
        List<SensorDataResponse> result = sensorDataService.getAllSensorData();

        // then
        assertThat(result).isEmpty();
    }
}
