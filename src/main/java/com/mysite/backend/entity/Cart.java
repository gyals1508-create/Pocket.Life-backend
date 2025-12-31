package com.mysite.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String text;

    @Column(nullable = false)
    private Boolean isBought = false;

    @Column(name = "shopping_date")
    private LocalDate shoppingDate;

    // ★ 즐겨찾기 상태 저장을 위한 필드 추가
    @Column(name = "is_favorite", nullable = false)
    private Boolean isFavorite = false;
}