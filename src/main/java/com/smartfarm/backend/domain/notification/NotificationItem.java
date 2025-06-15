package com.smartfarm.backend.domain.notification;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime; // ISO 8601 형식에 맞춰 LocalDateTime 사용

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "notifications") // MongoDB 컬렉션 이름 지정
public class NotificationItem {

    @Id
    private String id; // 명세에는 "number"로 되어 있지만, MongoDB ObjectId를 위해 String 사용

    private String plantId;
    private String type; // warning, info, error 등
    private String message;
    private LocalDateTime timestamp; // "ISO 8601" 형식에 맞춰 LocalDateTime 사용
    private boolean isRead;

    public NotificationItem(String plantId, String type, String message, LocalDateTime timestamp, boolean isRead) {
        this.plantId = plantId;
        this.type = type;
        this.message = message;
        this.timestamp = timestamp;
        this.isRead = isRead;
    }
}