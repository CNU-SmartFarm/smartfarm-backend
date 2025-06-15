package com.smartfarm.backend.repository;

import com.smartfarm.backend.domain.notification.NotificationItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationItemRepository extends MongoRepository<NotificationItem, String> {
    // 특정 plantId에 대한 알림 조회
    List<NotificationItem> findByPlantId(String plantId);
    // 특정 plantId에 대한 읽지 않은 알림 조회
    List<NotificationItem> findByPlantIdAndIsReadFalse(String plantId);
}