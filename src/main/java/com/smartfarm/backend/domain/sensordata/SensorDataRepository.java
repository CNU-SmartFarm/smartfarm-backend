package com.smartfarm.backend.domain.sensordata;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SensorDataRepository extends MongoRepository<SensorData, String> {
    Optional<SensorData> findTopByOrderByTimestampDesc();
    List<SensorData> findByDeviceId(String deviceId);
    Optional<SensorData> findTopByDeviceIdOrderByTimestampDesc(String deviceId);
}
