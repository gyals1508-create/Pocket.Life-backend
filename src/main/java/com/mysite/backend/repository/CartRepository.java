package com.mysite.backend.repository;

import com.mysite.backend.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    // 1. 특정 날짜의 목록을 가져오는 기본 메서드
    List<Cart> findAllByShoppingDate(LocalDate shoppingDate);

    // 2. 검색 기능: 텍스트가 포함된 모든 구매 이력을 조회 (30일 딸기 찾기용)
    List<Cart> findByTextContaining(String text);

    // 3. 즐겨찾기 유지 기능: 특정 날짜 항목이거나, 즐겨찾기(isFavorite)가 true인 항목 전체 조회
    @Query("SELECT c FROM Cart c WHERE c.shoppingDate = :shoppingDate OR c.isFavorite = true")
    List<Cart> findAllByShoppingDateOrIsFavoriteTrue(@Param("shoppingDate") LocalDate shoppingDate);
}