package com.mysite.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "meal")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 팀원 의견 반영: 길이를 30으로 제한하고 필수값(NOT NULL) 설정
    @Column(name = "menu_name", length = 30, nullable = false)
    private String text;

    @Column(name = "meal_type", length = 20)
    private String mealType;

    @Column(name = "meal_date")
    private LocalDate mealDate;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}