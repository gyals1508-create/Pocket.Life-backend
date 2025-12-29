package com.mysite.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity // DB 테이블과 매핑됨
@Getter @Setter // 게터/세터 자동 생성
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String menuName; // 식단 메뉴 이름

    private LocalDateTime createdAt = LocalDateTime.now(); // 생성 시간

    // 친구들용 주석: 이 클래스가 있어야 Repository에서 에러가 안 나!
}