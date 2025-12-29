package com.mysite.backend.repository;

import com.mysite.backend.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // DB에 접근하는 컴포넌트임을 선언해
public interface MealRepository extends JpaRepository<Meal, Long> {
    // JpaRepository를 상속받으면 기본적인 저장, 조회 기능은 자동으로 만들어져!
    // <엔티티 클래스명, ID의 타입> 순서로 적어주면 돼.
}