package com.smartfarm.backend.repository;

import com.smartfarm.backend.domain.plant.Plant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantRepository extends MongoRepository<Plant, String> {
}