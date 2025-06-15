package com.smartfarm.backend.repository;

import com.smartfarm.backend.domain.historicaldata.HistoricalDataPoint;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricalDataPointRepository extends MongoRepository<HistoricalDataPoint, String> {
    // 특정 plantId에 대한 과거 데이터 조회
    List<HistoricalDataPoint> findByPlantIdOrderByDateAscTimeAsc(String plantId);
    // 특정 plantId와 날짜에 대한 과거 데이터 조회
    List<HistoricalDataPoint> findByPlantIdAndDate(String plantId, String date);
}