package com.smartfarm.backend.domain; // 패키지명은 프로젝트 구조에 따라 조정하세요

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@Document(collection = "settings") // MongoDB 컬렉션 이름 지정
public class Settings {

    @Id
    private String userId; // 명세에 userId가 ID 역할을 하므로 @Id로 지정

    private boolean pushNotificationEnabled;
    private String language;
    private String theme;

    public Settings(String userId, boolean pushNotificationEnabled, String language, String theme) {
        this.userId = userId;
        this.pushNotificationEnabled = pushNotificationEnabled;
        this.language = language;
        this.theme = theme;
    }
}