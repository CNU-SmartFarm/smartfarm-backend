package com.smartfarm.backend.repository;

import com.smartfarm.backend.domain.Settings;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// Settings는 userId가 ID 역할을 하므로, String 타입으로 ID를 지정
@Repository
public interface SettingsRepository extends MongoRepository<Settings, String> {
    // userId로 설정 조회는 기본 findById(userId)로 가능
}