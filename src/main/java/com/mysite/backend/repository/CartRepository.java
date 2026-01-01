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

    List<Cart> findAllByShoppingDate(LocalDate shoppingDate);

    List<Cart> findByTextContaining(String text);

    // 수정: 특정 이름을 가진 항목 중 하나라도 즐겨찾기(true)인 것이 있는지 확인용
    boolean existsByTextAndIsFavoriteTrue(String text);

    // 수정: 특정 이름을 가진 모든 항목의 즐겨찾기 상태를 한꺼번에 변경하기 위함
    List<Cart> findAllByText(String text);

    @Query("SELECT c FROM Cart c WHERE c.shoppingDate = :shoppingDate OR c.isFavorite = true")
    List<Cart> findAllByShoppingDateOrIsFavoriteTrue(@Param("shoppingDate") LocalDate shoppingDate);
}