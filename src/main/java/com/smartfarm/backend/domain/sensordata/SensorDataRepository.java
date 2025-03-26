package com.smartfarm.backend.domain.sensordata;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SensorDataRepository extends MongoRepository<SensorData, String> {
    Optional<SensorData> findTopByOrderByTimestampDesc();
}
